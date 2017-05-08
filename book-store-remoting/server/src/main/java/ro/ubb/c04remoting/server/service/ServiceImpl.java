package ro.ubb.c04remoting.server.service;

import ro.ubb.c04remoting.common.BookStoreException;
import ro.ubb.c04remoting.common.domain.BaseEntity;
import ro.ubb.c04remoting.common.service.Service;
import ro.ubb.c04remoting.common.service.ServiceException;
import ro.ubb.c04remoting.server.repository.Repository;
import ro.ubb.c04remoting.server.repository.RepositoryException;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by nicu on 4/24/2017.
 */
public abstract class ServiceImpl<ID, T extends BaseEntity<ID>> implements Service<ID, T> {

    protected Repository<ID, T> repository;

    public ServiceImpl(Repository<ID, T> repository) {
        this.repository = repository;
    }

    @Override
    public Optional<T> add(T entity) {
        try {
            return repository.save(entity);
        } catch (BookStoreException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<T> delete(ID id) {
        try {
            return repository.delete(id);
        } catch (BookStoreException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<T> update(T entity) {
        try {
            return repository.update(entity);
        } catch (BookStoreException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Set<T> getAll() {
        try {
            return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toSet());
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Optional<T> findOne(ID id) {
        try {
            return repository.findOne(id);
        } catch (BookStoreException e) {
            throw new ServiceException(e);
        }
    }
}
