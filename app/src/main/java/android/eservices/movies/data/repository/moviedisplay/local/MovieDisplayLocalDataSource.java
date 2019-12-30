package android.eservices.movies.data.repository.moviedisplay.local;

import android.eservices.movies.data.db.MovieDatabase;
import android.eservices.movies.data.entity.MovieEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class MovieDisplayLocalDataSource {

    private MovieDatabase movieDatabase;

    public MovieDisplayLocalDataSource(MovieDatabase movieDatabase) {
        this.movieDatabase = movieDatabase;
    }

    public Flowable<List<MovieEntity>> loadFavorites() {
        return movieDatabase.movieDao().loadFavorites();
    }

    public Completable addMovieToFavorites(MovieEntity movieEntity) {
        return movieDatabase.movieDao().addMovieToFavorites(movieEntity);
    }

    public Completable deleteMovieFromFavorites(Long id) {
        return movieDatabase.movieDao().deleteMovieFromFavorites(id);
    }

    public Single<List<Long>> getFavoriteIdList() {
        return movieDatabase.movieDao().getFavoriteIdList();
    }
}
