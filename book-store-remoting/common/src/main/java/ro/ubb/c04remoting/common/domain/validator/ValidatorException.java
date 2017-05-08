package ro.ubb.c04remoting.common.domain.validator;

import ro.ubb.c04remoting.common.BookStoreException;

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
