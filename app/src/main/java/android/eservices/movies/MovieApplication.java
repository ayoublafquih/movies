package android.eservices.movies;

import android.app.Application;
import android.eservices.movies.data.di.FakeDependencyInjection;
import com.facebook.stetho.Stetho;

public class MovieApplication extends Application {

    public static final String API_KEY = "69da86e679e8246de82ebe8ec531fd08";

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        FakeDependencyInjection.setContext(this);
    }
}
