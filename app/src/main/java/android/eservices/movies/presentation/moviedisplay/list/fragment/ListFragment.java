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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListFragment extends Fragment implements MovieListContract.View, MovieActionInterface {
    public static String TAB_NAME = "MOST POPULAR";
    private View rootView;
    MovieListContract.Presenter movieListPresenter;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private RecyclerView.LayoutManager layoutManager;
    public static String SORT_BY = "popular";

    public ListFragment(String sortBy) {
        this.SORT_BY = sortBy;
        this.TAB_NAME = sortBy == "popular" ? "MOST POPULAR" : "TOP RATED";
        layoutManager= new LinearLayoutManager(getContext());
    }

    public static ListFragment newInstance(String sortBy) {
        return new ListFragment(sortBy);
    }

    public void changeLayout(){
        if (layoutManager instanceof GridLayoutManager) {
            layoutManager = new LinearLayoutManager(getContext());
        } else {
            layoutManager = new GridLayoutManager(getContext(), 2);
        }
        recyclerView.setLayoutManager(layoutManager);
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
        movieListPresenter.searchMovies(SORT_BY);
        movieListPresenter.attachView(this);
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void displayMovies(List<Movie> movieItemViewModelList) {
        movieAdapter.bindViewModels(movieItemViewModelList);
    }

    @Override
    public void onFavoriteToggle(Long movieId, boolean isFavorite) {
        if (isFavorite) {
            movieListPresenter.addMovieToFavorite(movieId);
            System.err.println("add movie");
        } else {
            movieListPresenter.removeMovieFromFavorites(movieId);
            System.err.println("remove movie");

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