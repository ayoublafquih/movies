package android.eservices.movies.presentation.moviedisplay.list;

import android.eservices.movies.data.api.model.Movie;
import java.util.List;

/**
 * It is an interface that represent movie contract
 */

public class MovieListContract {

    public interface View {
        void displayMovies(List<Movie> movieItemViewModelList);

        void onMovieAddedToFavorites();

        void onMovieRemovedFromFavorites();

    }

    public interface Presenter {
        void searchMovies(String keywords);

        void attachView(View view);

        void addMovieToFavorite(Long movieId);

        void removeMovieFromFavorites(Long movieId);

        void detachView();
    }
}
