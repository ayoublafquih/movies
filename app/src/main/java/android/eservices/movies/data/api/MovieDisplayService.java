package android.eservices.movies.data.api;

import android.eservices.movies.data.api.model.Movie;
import android.eservices.movies.data.api.model.Movies;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * It is interface to describe end points
 * @author ayoub
 */

public interface MovieDisplayService {

    /**
     * @param sortBy has to values Most Popular or Top Rated
     * @param apiKey for our web service
     * @return
     */
    @GET("/3/movie/{sort_by}")
    Single<Movies> getMovies(@Path("sort_by") String sortBy, @Query("api_key") String apiKey);

    /**
     * @param id  for a specific movie
     * @param apiKey for our web service
     * @return a detailed movie
     */
    @GET("/3/movie/{id}")
    Single<Movie> getMovie(@Path("id") long id, @Query("api_key") String apiKey);

}
