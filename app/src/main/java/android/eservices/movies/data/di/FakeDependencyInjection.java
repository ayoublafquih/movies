package android.eservices.movies.data.di;


import android.content.Context;
import android.eservices.movies.data.db.MovieDatabase;
import androidx.room.Room;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FakeDependencyInjection {

    //private static BookDisplayService bookDisplayService;
    private static Retrofit retrofit;
    private static Gson gson;
//    private static BookDisplayRepository bookDisplayRepository;
    private static MovieDatabase movieDatabase;
    private static Context applicationContext;


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
