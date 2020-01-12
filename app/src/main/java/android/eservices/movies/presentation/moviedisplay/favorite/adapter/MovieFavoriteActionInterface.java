package android.eservices.movies.presentation.moviedisplay.favorite.adapter;

/**
 * It is an interface that define a method to handle movies action
 */
public interface MovieFavoriteActionInterface {
    void onRemoveFavorite(Long movieId, Boolean isChecked);
}
