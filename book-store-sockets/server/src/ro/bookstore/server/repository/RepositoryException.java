package ro.bookstore.server.repository;

import ro.bookstore.common.BookStoreException;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class RepositoryException extends BookStoreException {
    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
