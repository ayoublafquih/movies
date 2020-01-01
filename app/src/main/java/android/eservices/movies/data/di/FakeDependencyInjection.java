package android.eservices.movies.data.di;


import android.content.Context;
import android.eservices.movies.data.api.MovieDisplayService;
import android.eservices.movies.data.db.MovieDatabase;
import android.eservices.movies.data.repository.moviedisplay.MovieDisplayDataRepository;
import android.eservices.movies.data.repository.moviedisplay.MovieDisplayRepository;
import android.eservices.movies.data.repository.moviedisplay.local.MovieDisplayLocalDataSource;
import android.eservices.movies.data.repository.moviedisplay.mapper.MovieToMovieEntityMapper;
import android.eservices.movies.data.repository.moviedisplay.remote.MovieDisplayRemoteDataSource;

import androidx.room.Room;

import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeDependencyInjection {

    private static MovieDisplayService movieDisplayService;
    private static Retrofit retrofit;
    private static Gson gson;
    private static MovieDisplayRepository movieDisplayRepository;
    private static MovieDatabase movieDatabase;
    private static Context applicationContext;

    public static MovieDisplayRepository getMovieDisplayRepository() {
        if (movieDisplayRepository == null) {
            movieDisplayRepository = new MovieDisplayDataRepository(
                    new MovieDisplayLocalDataSource(getMovieDatabase()),
                    new MovieDisplayRemoteDataSource(getMovieDisplayService()),
                    new MovieToMovieEntityMapper()
            );
        }
        return movieDisplayRepository;
    }

    public static MovieDisplayService getMovieDisplayService() {
        if (movieDisplayService == null) {
            movieDisplayService = getRetrofit().create(MovieDisplayService.class);
        }
        return movieDisplayService;
    }


    public static Retrofit getRetrofit() {
        if (retrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(interceptor)
                    .addNetworkInterceptor(new StethoInterceptor())
                    .build();

            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.themoviedb.org/")
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();
        }
        return retrofit;
    }

    public static Gson getGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static void setContext(Context context) {
        applicationContext = context;
    }

    public static MovieDatabase getMovieDatabase() {
        if (movieDatabase == null) {
            movieDatabase = Room.databaseBuilder(applicationContext,
                    MovieDatabase.class, "movie-database").build();
        }
        return movieDatabase;
    }
}
