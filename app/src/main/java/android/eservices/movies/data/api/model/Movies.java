package android.eservices.movies.data.api.model;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * It is a class that represent a response webservice to handle List of movies
 */
public class Movies {
    @SerializedName("results")
    private List<Movie> movies = new ArrayList<>();

    public List<Movie> getMovies() {
        return movies;
    }
}
