package android.eservices.movies.presentation.moviedisplay.favorite.mapper;

import android.eservices.movies.data.api.model.Movie;
import android.eservices.movies.data.entity.MovieEntity;
import java.util.ArrayList;
import java.util.List;

public class MovieEntityToMovieMapper {

    public Movie map(MovieEntity movieEntity) {
        Movie movie = new Movie();

        movie.setId(movieEntity.getId());
        movie.setBackdrop_path(movieEntity.getBackdrop_path());
        movie.setPoster_path(movieEntity.getPoster_path());
        movie.setRelease_date(movieEntity.getRelease_date());
        movie.setVote_average(movieEntity.getVote_average());
        movie.setTitle(movieEntity.getTitle());
        movie.setOverview(movieEntity.getOverview());

        return movie;
    }

    public List<Movie> map(List<MovieEntity> movieList) {
        List<Movie> movieItemViewModelList = new ArrayList<>();
        for (MovieEntity movie : movieList) {
            movieItemViewModelList.add(map(movie));
        }
        return movieItemViewModelList;
    }
}
