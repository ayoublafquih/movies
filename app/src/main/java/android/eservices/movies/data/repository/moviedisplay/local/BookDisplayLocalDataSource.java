package android.eservices.movies.data.repository.moviedisplay.local;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class BookDisplayLocalDataSource {

    private BookDatabase bookDatabase;

    public BookDisplayLocalDataSource(BookDatabase bookDatabase) {
        this.bookDatabase = bookDatabase;
    }

    public Flowable<List<BookEntity>> loadFavorites() {
        return bookDatabase.bookDao().loadFavorites();
    }

    public Completable addBookToFavorites(BookEntity bookEntity) {
        return bookDatabase.bookDao().addBookToFavorites(bookEntity);
    }

    public Completable deleteBookFromFavorites(String id) {
        return bookDatabase.bookDao().deleteBookFromFavorites(id);
    }

    public Single<List<String>> getFavoriteIdList() {
        return bookDatabase.bookDao().getFavoriteIdList();
    }
}
