package android.eservices.movies.data.repository.moviedisplay.mapper;

import android.eservices.movies.data.api.model.Movie;
import android.eservices.movies.data.entity.MovieEntity;
import android.text.TextUtils;

public class MovieToMovieEntityMapper {

    public MovieEntity map(Movie movie) {
        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setId(movie.getId());
        movieEntity.setBackdrop_path(movie.getBackdrop_path());
        movieEntity.setPoster_path(movie.getPoster_path());
        movieEntity.setRelease_date(movie.getRelease_date());
        movieEntity.setVote_average(movie.getVote_average());
        movieEntity.setTitle(movie.getTitle());
        movieEntity.setOverview(movie.getOverview());

        return movieEntity;
    }
}
