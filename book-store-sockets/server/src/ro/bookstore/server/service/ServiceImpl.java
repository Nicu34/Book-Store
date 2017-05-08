package ro.bookstore.server.service;

import ro.bookstore.common.BookStoreException;
import ro.bookstore.common.domain.BaseEntity;
import ro.bookstore.common.service.Service;
import ro.bookstore.common.service.ServiceException;
import ro.bookstore.server.repository.Repository;
import ro.bookstore.server.repository.RepositoryException;

import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by nicu on 4/24/2017.
 */
public abstract class ServiceImpl<ID, T extends BaseEntity<ID>> implements Service<ID, T> {

    protected Repository<ID, T> repository;
    protected ExecutorService executorService;

    public ServiceImpl(Repository<ID, T> repository, ExecutorService executorService) {
        this.repository = repository;
        this.executorService = executorService;
    }

    @Override
    public CompletableFuture<T> add(T entity) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return repository.save(entity).get();
            } catch (BookStoreException e) {
                throw new ServiceException(e);
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<T> delete(ID id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return repository.delete(id).get();
            } catch (BookStoreException e) {
                throw new ServiceException(e);
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<T> update(T entity) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return repository.update(entity).get();
            } catch (BookStoreException e) {
                throw new ServiceException(e);
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<Set<T>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
        try {
            Set<T> set = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toSet());
            System.out.println("SERVER " + set);
            return set;
        } catch (RepositoryException e) {
                throw new ServiceException(e);
        }
    }, executorService);
}

    @Override
    public CompletableFuture<T> findOne(ID id) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return repository.findOne(id).get();
            }
            catch (BookStoreException e) {
                throw new ServiceException(e);
            }
        }, executorService);
    }
}
