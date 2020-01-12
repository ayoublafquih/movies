package android.eservices.movies.data.repository.moviedisplay.local;

import android.eservices.movies.data.db.MovieDatabase;
import android.eservices.movies.data.entity.MovieEntity;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

/**
 * Is class to handle a local database
 * @author ayoub
 *
 */

public class MovieDisplayLocalDataSource {

    private MovieDatabase movieDatabase;

    public MovieDisplayLocalDataSource(MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
    }

    /**
     *  Return List of favorites movies from local Database
     */
    public Flowable<List<MovieEntity>> loadFavorites() {
        return movieDatabase.movieDao().loadFavorites();
    }

    /**
     *  Add movie to local Database
     */
    public Completable addMovieToFavorites(MovieEntity movieEntity) {
        return movieDatabase.movieDao().addMovieToFavorites(movieEntity);
    }

    /**
     *  Delete movie from local Database
     */
    public Completable deleteMovieFromFavorites(Long id) {
        return movieDatabase.movieDao().deleteMovieFromFavorites(id);
    }

    /**
     *  Return list of movies ID from local database
     */
    public Single<List<Long>> getFavoriteIdList() {
        return movieDatabase.movieDao().getFavoriteIdList();
    }
}
