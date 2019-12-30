package android.eservices.movies.data.repository.moviedisplay;

import android.eservices.movies.data.api.model.Movies;
import android.eservices.movies.data.api.model.Reviews;
import android.eservices.movies.data.api.model.Trailers;
import android.eservices.movies.data.entity.MovieEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface MovieDisplayRepository {

    Single<Movies> getMovies(String sortBy);

    Single<Reviews> getReviewsMovie(long id);

    Single<Trailers> getTrailersMovie(long id);

    Flowable<List<MovieEntity>> getFavoriteMovies();

    Completable addMovieToFavorites(Long MovieId);

    Completable removeMovieFromFavorites(Long movieId);


}
