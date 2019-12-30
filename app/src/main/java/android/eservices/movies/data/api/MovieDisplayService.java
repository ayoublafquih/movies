package android.eservices.movies.data.api;

import android.eservices.movies.data.api.model.Movies;
import android.eservices.movies.data.api.model.Reviews;
import android.eservices.movies.data.api.model.Trailers;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieDisplayService {

    @GET("/3/movie/{sort_by}")
    Single<Movies> getMovie(@Path("sort_by") String sortBy, @Query("api_key") String apiKey);

    @GET("/3/movie/{id}/reviews")
    Single<Reviews> getReviewsMovie(@Path("id") long id, @Query("api_key") String apiKey);

    @GET("/3/movie/{id}/videos")
    Single<Trailers> getTrailersMovie(@Path("id") long id, @Query("api_key") String apiKey);

}
