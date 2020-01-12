package android.eservices.movies.presentation.moviedisplay.favorite;

import android.eservices.movies.data.api.model.Movie;
import java.util.List;

/**
 * It is an interface that represent favorite movie contract
 */

public interface MovieFavoriteContract {

    interface View {
        void displayFavorites(List<Movie> movieDetailViewModelList);
        void onMovieRemoved();
    }

    interface Presenter {
        void attachView(View view);
        void getFavorites();
        void removeMovieFromFavorites(Long movieId);
        void detachView();
    }
}
