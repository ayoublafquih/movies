package android.eservices.movies.data.repository.moviedisplay;

import android.eservices.movies.data.api.model.Movie;
import android.eservices.movies.data.api.model.Movies;
import android.eservices.movies.data.entity.MovieEntity;
import android.eservices.movies.data.repository.moviedisplay.local.MovieDisplayLocalDataSource;
import android.eservices.movies.data.repository.moviedisplay.mapper.MovieToMovieEntityMapper;
import android.eservices.movies.data.repository.moviedisplay.remote.MovieDisplayRemoteDataSource;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;


/**
 * It is an implementation of our Data repository
 */
public class MovieDisplayDataRepository implements MovieDisplayRepository {

    private MovieDisplayLocalDataSource movieDisplayLocalDataSource;
    private MovieDisplayRemoteDataSource movieDisplayRemoteDataSource;
    private MovieToMovieEntityMapper movieToMovieEntityMapper;


    public MovieDisplayDataRepository(MovieDisplayLocalDataSource movieDisplayLocalDataSource,
                                      MovieDisplayRemoteDataSource movieDisplayRemoteDataSource,
                                      MovieToMovieEntityMapper movieToMovieEntityMapper) {
        this.movieDisplayLocalDataSource = movieDisplayLocalDataSource;
        this.movieDisplayRemoteDataSource = movieDisplayRemoteDataSource;
        this.movieToMovieEntityMapper = movieToMovieEntityMapper;
    }

    /**
     * It is a method which allows to add movie to our list of favorites movies
     * @param movieId
     * @return RrJs Type to know that movie is added or not
     */
    @Override
    public Completable addMovieToFavorites(Long movieId) {

        return movieDisplayRemoteDataSource.getMovie(movieId)
                .map(new Function<Movie, MovieEntity>() {
                    @Override
                    public MovieEntity apply(Movie movie) {
                        return movieToMovieEntityMapper.map(movie);
                    }
                })
                .flatMapCompletable(new Function<MovieEntity, CompletableSource>() {
                    @Override
                    public CompletableSource apply(MovieEntity movieEntity) {
                        return movieDisplayLocalDataSource.addMovieToFavorites(movieEntity);
                    }
                });
    }

    /**
     * It is a method which allows to remove movie from our list of favorites movies
     * @param movieId
     * @return RrJs Type to know that movie is added or not
     */

    @Override
    public Completable removeMovieFromFavorites(Long movieId) {
        return movieDisplayLocalDataSource.deleteMovieFromFavorites(movieId);
    }

    /**
     * It is a method which allows to add movie to our list of favorites movies
     * @return RrJs Type that contain all favorites movies
     */
    @Override
    public Flowable<List<MovieEntity>> getFavoriteMovies() {
        return movieDisplayLocalDataSource.loadFavorites();
    }

    /**
     * @return RrJs Type that return list of movie from remote database
     */
    @Override
    public Single<Movies> getMovies(String sortBy) {
        return movieDisplayRemoteDataSource.getMovies(sortBy)
                .zipWith(movieDisplayLocalDataSource.getFavoriteIdList(), new BiFunction<Movies, List<Long>, Movies>() {
                    @Override
                    public Movies apply(Movies movies, List<Long> longs) throws Exception {
                        for (Movie movie : movies.getMovies()) {
                            if (longs.contains(movie.getId())) {
                                movie.setFavorite();
                            }
                        }
                        return movies;
                    }
                });
    }

}
