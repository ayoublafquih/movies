package android.eservices.movies.presentation.moviedisplay.list.fragment;

import android.eservices.movies.R;
import android.eservices.movies.data.api.model.Movie;
import android.eservices.movies.data.di.FakeDependencyInjection;
import android.eservices.movies.presentation.moviedisplay.list.MovieListContract;
import android.eservices.movies.presentation.moviedisplay.list.MovieListPresenter;
import android.eservices.movies.presentation.moviedisplay.list.adapter.MovieActionInterface;
import android.eservices.movies.presentation.moviedisplay.list.adapter.MovieAdapter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListFragment extends Fragment implements MovieListContract.View, MovieActionInterface {
    public static final String TAB_NAME = "List";
    private View rootView;
    MovieListContract.Presenter movieListPresenter;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    public static final String MOST_POPULAR = "popular";


    public ListFragment() {
    }

    public static ListFragment newInstance() {
        return new ListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_list, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        movieListPresenter = new MovieListPresenter(FakeDependencyInjection.getMovieDisplayRepository());
        movieListPresenter.searchMovies(MOST_POPULAR);
        movieListPresenter.attachView(this);
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void displayMovies(List<Movie> movieItemViewModelList) {
        movieAdapter.bindViewModels(movieItemViewModelList);
    }

    @Override
    public void onFavoriteToggle(Long movieId, boolean isFavorite) {
        if (isFavorite) {
            movieListPresenter.addMovieToFavorite(movieId);
        } else {
            movieListPresenter.removeMovieFromFavorites(movieId);
        }
    }


    @Override
    public void onMovieAddedToFavorites() {
        //Do nothing
    }

    @Override
    public void onMovieRemovedFromFavorites() {
        //Do nothing
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieListPresenter.detachView();
    }
}