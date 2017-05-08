package ro.bookstore.client.ui;

import ro.bookstore.common.BookStoreException;

/**
 * Created by MuresanN on 3/15/2017.
 */
public class ConsoleException extends BookStoreException {
    public ConsoleException(String message) {
        super(message);
    }

    public ConsoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConsoleException(Throwable cause) {
        super(cause);
    }
}
