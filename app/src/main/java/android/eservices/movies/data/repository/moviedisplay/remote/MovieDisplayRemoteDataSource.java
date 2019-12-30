package android.eservices.movies.data.repository.moviedisplay.remote;

import android.eservices.movies.MovieApplication;
import android.eservices.movies.data.api.MovieDisplayService;
import android.eservices.movies.data.api.model.Movie;
import android.eservices.movies.data.api.model.Movies;
import android.eservices.movies.data.api.model.Reviews;
import android.eservices.movies.data.api.model.Trailers;

import io.reactivex.Single;

public class MovieDisplayRemoteDataSource {

    private MovieDisplayService movieDisplayService;

    public MovieDisplayRemoteDataSource(MovieDisplayService movieDisplayService) {
        this.movieDisplayService = movieDisplayService;
    }

    public Single<Movies> getMovies(String sortBy) {
        return movieDisplayService.getMovies(sortBy, MovieApplication.API_KEY);
    }

    public Single<Movie> getMovie(Long idMovie) {
        return movieDisplayService.getMovie(idMovie, MovieApplication.API_KEY);
    }

    public Single<Reviews> getReviewsMovie(Long movieId) {
        return movieDisplayService.getReviewsMovie(movieId, MovieApplication.API_KEY);
    }

    public Single<Trailers> getTrailersMovie(long movieId) {
        return movieDisplayService.getTrailersMovie(movieId, MovieApplication.API_KEY);
    }
}
