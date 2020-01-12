package android.eservices.movies.data.repository.moviedisplay.remote;

import android.eservices.movies.MovieApplication;
import android.eservices.movies.data.api.MovieDisplayService;
import android.eservices.movies.data.api.model.Movie;
import android.eservices.movies.data.api.model.Movies;
import io.reactivex.Single;

/**
 * It is a class to handle a remote Data
 * @author ayoub
 *
 */

public class MovieDisplayRemoteDataSource {

    private MovieDisplayService movieDisplayService;

    public MovieDisplayRemoteDataSource(MovieDisplayService movieDisplayService) {
        this.movieDisplayService = movieDisplayService;
    }

    /**
     * @param sortBy
     * @return a movie from remote database through web service
     */
    public Single<Movies> getMovies(String sortBy) {
        return movieDisplayService.getMovies(sortBy, MovieApplication.API_KEY);
    }

    /**
     * @param idMovie
     * @return a detail movie from remote database through web service
     */
    public Single<Movie> getMovie(Long idMovie) {
        return movieDisplayService.getMovie(idMovie, MovieApplication.API_KEY);
    }

}
