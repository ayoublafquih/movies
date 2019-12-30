package android.eservices.movies.data.repository.moviedisplay.remote;

import android.eservices.movies.BookApplication;

import io.reactivex.Single;

public class BookDisplayRemoteDataSource {

    private BookDisplayService bookDisplayService;

    public BookDisplayRemoteDataSource(BookDisplayService bookDisplayService) {
        this.bookDisplayService = bookDisplayService;
    }

    public Single<BookSearchResponse> getBookSearchResponse(String keywords) {
        return bookDisplayService.searchBooks(keywords, BookApplication.API_KEY);
    }

    public Single<Book> getBookDetails(String bookId) {
        return bookDisplayService.getBook(bookId, BookApplication.API_KEY);
    }
}
