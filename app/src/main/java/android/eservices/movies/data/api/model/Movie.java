package android.eservices.movies.data.api.model;

import android.content.Context;
import android.eservices.movies.R;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * It is a class that represent response Movie from Web Service
 */

public class Movie {

    public static final String TAG = Movie.class.getName();

    private long id;
    private String vote_average;
    private String title;
    private String poster_path;
    private String overview;
    private String release_date;
    private String backdrop_path;
    private boolean isFavorite;

    public Movie() {
    }

    public Movie(long id, String vote_average, String title, String poster_path,
                 String overview, String release_date, String backdrop_path) {
        this.id = id;
        this.vote_average = vote_average;
        this.title = title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.backdrop_path = backdrop_path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getPosterPath(Context context) {
        if (poster_path != null && !poster_path.isEmpty()) {
            return context.getResources().getString(R.string.url_for_downloading_poster) + poster_path;
        }
        return null;
    }

    public String getReleaseDate(Context context) {
        String inputPatter = "yyyy-dd-MM";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(inputPatter, Locale.US);
        if (release_date != null && !release_date.isEmpty()) {
            try {
                Date date = simpleDateFormat.parse(release_date);
                return DateFormat.getDateInstance().format(date);
            } catch (ParseException e) {
                Log.e(TAG, "error to parse: " + release_date);
            }
        } else {
            release_date = "Release date is null";
        }
        return release_date;
    }


    public String getBackdropPath(Context context) {
        if (backdrop_path != null && !backdrop_path.isEmpty()) {
            return context.getResources().getString(R.string.url_for_downloading_backdrop) +
                    backdrop_path;
        }
        return null;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite() {
        isFavorite = true;
    }
}
