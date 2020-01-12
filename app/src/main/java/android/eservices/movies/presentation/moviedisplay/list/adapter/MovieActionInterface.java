package android.eservices.movies.presentation.moviedisplay.list.adapter;

/**
 * It is an interface that define a method to handle movies action interface
 */
public interface MovieActionInterface {
    void onFavoriteToggle(Long movieId, boolean isFavorite);
}
