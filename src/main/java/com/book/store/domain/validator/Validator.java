package com.book.store.domain.validator;

/**
 * Created by MuresanN on 3/8/2017.
 */
public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
