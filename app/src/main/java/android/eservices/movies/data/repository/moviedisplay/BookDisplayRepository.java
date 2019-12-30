package android.eservices.movies.data.repository.moviedisplay;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public interface BookDisplayRepository {

    Single<BookSearchResponse> getBookSearchResponse(String keywords);

    Flowable<List<BookEntity>> getFavoriteBooks();

    Completable addBookToFavorites(String bookId);

    Completable removeBookFromFavorites(String bookId);
}
