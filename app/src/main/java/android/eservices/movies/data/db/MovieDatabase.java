package android.eservices.movies.data.db;

import android.eservices.movies.data.entity.MovieEntity;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MovieEntity.class}, version = 1, exportSchema = false)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDao movieDao();
}