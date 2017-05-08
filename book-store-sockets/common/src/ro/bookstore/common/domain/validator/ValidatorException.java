package ro.bookstore.common.domain.validator;

import ro.bookstore.common.BookStoreException;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class ValidatorException extends BookStoreException {
    public ValidatorException(String message) {
        super(message);
    }

    public ValidatorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidatorException(Throwable cause) {
        super(cause);
    }
}
