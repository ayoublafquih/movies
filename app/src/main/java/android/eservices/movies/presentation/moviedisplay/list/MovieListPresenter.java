package android.eservices.movies.presentation.moviedisplay.list;

import android.eservices.movies.data.api.model.Movies;
import android.eservices.movies.data.repository.moviedisplay.MovieDisplayRepository;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MovieListPresenter implements MovieListContract.Presenter {

    private MovieDisplayRepository movieDisplayRepository;
    private MovieListContract.View view;
    private CompositeDisposable compositeDisposable;


    public MovieListPresenter(MovieDisplayRepository movieDisplayRepository) {
        this.movieDisplayRepository = movieDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void searchMovies(String sortBy) {
        compositeDisposable.clear();
        compositeDisposable.add(movieDisplayRepository.getMovies(sortBy)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Movies>() {

                    @Override
                    public void onSuccess(Movies movies) {
                        // work with the resulting todos
                        view.displayMovies(movies.getMovies());

                    }

                    @Override
                    public void onError(Throwable e) {
                        // handle the error case
                        System.out.println(e.toString());
                    }
                }));

    }

    @Override
    public void addMovieToFavorite(Long movieId) {
        compositeDisposable.add(movieDisplayRepository.addMovieToFavorites(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onMovieAddedToFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void removeMovieFromFavorites(Long movieId) {
        compositeDisposable.add(movieDisplayRepository.removeMovieFromFavorites(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        view.onMovieRemovedFromFavorites();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void attachView(MovieListContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }




}
