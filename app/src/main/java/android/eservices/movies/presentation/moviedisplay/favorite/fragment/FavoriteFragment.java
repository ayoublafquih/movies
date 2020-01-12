package android.eservices.movies.presentation.moviedisplay.favorite.fragment;

import android.eservices.movies.R;
import android.eservices.movies.data.api.model.Movie;
import android.eservices.movies.data.di.FakeDependencyInjection;
import android.eservices.movies.presentation.moviedisplay.favorite.MovieFavoriteContract;
import android.eservices.movies.presentation.moviedisplay.favorite.MovieFavoritePresenter;
import android.eservices.movies.presentation.moviedisplay.favorite.adapter.MovieFavoriteActionInterface;
import android.eservices.movies.presentation.moviedisplay.favorite.adapter.MovieFavoriteAdapter;
import android.eservices.movies.presentation.moviedisplay.favorite.mapper.MovieEntityToMovieMapper;
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

import java.util.List;

public class FavoriteFragment extends Fragment implements MovieFavoriteContract.View, MovieFavoriteActionInterface {

    public static final String TAB_NAME = "Favorites";
    private View rootView;
    private CoordinatorLayout coordinatorLayout;
    MovieFavoriteContract.Presenter movieFavoritePresenter;
    private RecyclerView recyclerView;
    private MovieFavoriteAdapter movieFavoriteAdapter;
    private RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

    private FavoriteFragment() {
    }

    public static FavoriteFragment newInstance() {
        return new FavoriteFragment();
    }

    /**
     * This method allows us to change display format for listing movies
     */
    public void changeLayout() {
        if (layoutManager instanceof GridLayoutManager) {
            layoutManager = new LinearLayoutManager(getContext());
        } else {
            layoutManager = new GridLayoutManager(getContext(), 2);
        }
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Allows us to remove movie from Database
     *
     * @param movieId
     * @param isChecked
     */
    @Override
    public void onRemoveFavorite(Long movieId, Boolean isChecked) {
        if (!isChecked) {
            movieFavoritePresenter.removeMovieFromFavorites(movieId);
            displaySnackBar("remove movie from favorite");
        }
    }

    /**
     * This method allows us to display a message when a movie is deleted
     *
     * @param message to display
     */
    public void displaySnackBar(String message) {
        Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG)
                .show();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        coordinatorLayout = rootView.findViewById(R.id.coordinator_layout);
        super.onActivityCreated(savedInstanceState);
        setupRecyclerView();
        movieFavoritePresenter = new MovieFavoritePresenter(FakeDependencyInjection.getMovieDisplayRepository(), new MovieEntityToMovieMapper());
        movieFavoritePresenter.attachView(this);
        movieFavoritePresenter.getFavorites();
    }

    private void setupRecyclerView() {
        recyclerView = rootView.findViewById(R.id.recycler_view);
        movieFavoriteAdapter = new MovieFavoriteAdapter(this);
        recyclerView.setAdapter(movieFavoriteAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void displayFavorites(List<Movie> movieViewModelList) {
        movieFavoriteAdapter.bindViewModels(movieViewModelList);
    }

    @Override
    public void onMovieRemoved() {
        //Do nothing yet
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        movieFavoritePresenter.detachView();
    }
}
