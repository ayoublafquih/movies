package android.eservices.movies.data.db;

import android.eservices.movies.data.entity.MovieEntity;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;


@Dao
public interface MovieDao {

    @Query("SELECT * from movieentity")
    Flowable<List<MovieEntity>> loadFavorites();

    @Insert
    public Completable addMovieToFavorites(MovieEntity movieEntity);

    @Query("DELETE FROM movieentity WHERE id = :id")
    public Completable deleteMovieFromFavorites(Long id);

    @Query("SELECT id from movieentity")
    Single<List<Long>> getFavoriteIdList();
}
