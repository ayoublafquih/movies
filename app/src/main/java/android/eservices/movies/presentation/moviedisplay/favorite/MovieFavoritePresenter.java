package android.eservices.movies.presentation.moviedisplay.favorite;

import android.eservices.movies.data.entity.MovieEntity;
import android.eservices.movies.data.repository.moviedisplay.MovieDisplayRepository;
import android.eservices.movies.presentation.moviedisplay.favorite.mapper.MovieEntityToMovieMapper;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;

public class MovieFavoritePresenter implements MovieFavoriteContract.Presenter {

    private MovieDisplayRepository movieDisplayRepository;
    private MovieFavoriteContract.View view;
    private CompositeDisposable compositeDisposable;
    private MovieEntityToMovieMapper movieEntityToMovieMapper;


    public MovieFavoritePresenter(MovieDisplayRepository movieDisplayRepository, MovieEntityToMovieMapper movieEntityToMovieMapper) {
        this.movieDisplayRepository = movieDisplayRepository;
        this.compositeDisposable = new CompositeDisposable();
        this.movieEntityToMovieMapper = movieEntityToMovieMapper;
    }

    @Override
    public void attachView(MovieFavoriteContract.View view) {
        this.view = view;
    }

    @Override
    public void getFavorites() {
        compositeDisposable.add(movieDisplayRepository.getFavoriteMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<List<MovieEntity>>() {

                    @Override
                    public void onNext(List<MovieEntity> movieEntityList) {
                        view.displayFavorites(movieEntityToMovieMapper.map(movieEntityList));
                        System.out.println("BIND FAVORITES");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        //Do Nothing
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
                        view.onMovieRemoved();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    @Override
    public void detachView() {
        compositeDisposable.dispose();
        view = null;
    }
}
