package ro.bookstore.common;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class BookStoreException extends Exception {
    public BookStoreException(String message) {
        super(message);
    }

    public BookStoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookStoreException(Throwable cause) {
        super(cause);
    }
}
