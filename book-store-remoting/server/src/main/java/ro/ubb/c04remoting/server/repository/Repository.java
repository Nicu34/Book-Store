package ro.ubb.c04remoting.server.repository;

import ro.ubb.c04remoting.common.domain.BaseEntity;
import ro.ubb.c04remoting.common.domain.validator.ValidatorException;

import java.util.Optional;

/**
 * Created by MuresanN on 3/8/2017.
 */
public interface
Repository<ID, T extends BaseEntity<ID>> {
    /**
     * Finds one entity base on an ID.
     * @param id - generic type ID
     * @return Optional of entity - generic type T
     */
    Optional<T> findOne(ID id) throws RepositoryException;

    /**
     * Returns an iterable with all entities.
     * @return Iterable of entity - generic type T
     */
    Iterable<T> findAll() throws RepositoryException;

    /**
     * Saves an entity to repository.
     * @param entity - generic type T
     * @return Optional of entity - generic type T
     * @throws ValidatorException
     * @throws RepositoryException
     */
    Optional<T> save(T entity) throws ValidatorException, RepositoryException;

    /**
     * Deletes an entity from repository.
     * @param id - generic type ID
     * @return Optional of entity - generic type T
     * @throws RepositoryException
     */
    Optional<T> delete(ID id) throws RepositoryException;

    /**
     * Updates an entity from repository.
     * @param entity - generic type T
     * @return Optional of entity - generic type T
     * @throws RepositoryException
     */
    Optional<T> update(T entity) throws ValidatorException, RepositoryException;
}
