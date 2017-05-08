package ro.ubb.c04remoting.common.domain.validator;

/**
 * Created by MuresanN on 3/8/2017.
 */
@FunctionalInterface
public interface Validator<T> {
    void validate(T entity) throws ValidatorException;
}
