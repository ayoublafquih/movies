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
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements MovieListContract.View, MovieActionInterface {
    public static String TAB_NAME = "MOST POPULAR";
    private View rootView;
    MovieListContract.Presenter movieListPresenter;
    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
    List<Movie> movies = new ArrayList<>();
    public static String SORT_BY = "popular";
    private CoordinatorLayout coordinatorLayout;


    public ListFragment() {
    }

    public static ListFragment newInstance(String sortBy) {
        SORT_BY = sortBy;
        TAB_NAME = sortBy == "popular" ? "MOST POPULAR" : "TOP RATED";
        return new ListFragment();
    }

    public void displaySnackBar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    public void changeLayout() {
        if (layoutManager instanceof GridLayoutManager) {
            layoutManager = new LinearLayoutManager(getContext());
        } else {
            layoutManager = new GridLayoutManager(getContext(), 2);
        }
        recyclerView.setLayoutManager(layoutManager);
    }

    public void ChangeFilter(String sortBy) {
        SORT_BY = sortBy;
        setupRecyclerView();
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
        recyclerView = rootView.findViewById(R.id.recycler_view);
        movieAdapter = new MovieAdapter(this);
        recyclerView.setAdapter(movieAdapter);
        movieListPresenter = new MovieListPresenter(FakeDependencyInjection.getMovieDisplayRepository());
        movieListPresenter.attachView(this);
        coordinatorLayout = rootView.findViewById(R.id.coordinator_layout);
        setupRecyclerView();

    }

    public void setupRecyclerView() {
        recyclerView.setLayoutManager(layoutManager);
        movieListPresenter.searchMovies(SORT_BY);
    }

    @Override
    public void displayMovies(List<Movie> movieItemViewModelList) {
        movies = movieItemViewModelList;
        movieAdapter.bindViewModels(movieItemViewModelList);
    }

    @Override
    public void onFavoriteToggle(Long movieId, boolean isFavorite) {
        if (isFavorite) {
            movieListPresenter.addMovieToFavorite(movieId);
            displaySnackBar("Le film est ajouté avec succès");
        } else {
            movieListPresenter.removeMovieFromFavorites(movieId);
            displaySnackBar("le film est supprimé avec succès");
        }
    }


    @Override
    public void onMovieAddedToFavorites() {
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