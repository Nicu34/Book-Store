package ro.bookstore.common;

/**
 * Created by nicu on 4/24/2017.
 */
public class BookStoreRuntimeException extends RuntimeException {
    public BookStoreRuntimeException() {
    }

    public BookStoreRuntimeException(String message) {
        super(message);
    }

    public BookStoreRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookStoreRuntimeException(Throwable cause) {
        super(cause);
    }

    public BookStoreRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
