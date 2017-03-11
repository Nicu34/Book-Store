package com.book.store.service;

import com.book.store.domain.BaseEntity;
import com.book.store.domain.validator.ValidatorException;
import com.book.store.repository.Repository;
import com.book.store.repository.RepositoryException;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by nicu on 3/8/2017.
 */
public abstract class Service<ID, T extends BaseEntity<ID>> {
    private Repository<ID, T> repository;

    public Service(Repository<ID, T> repository) {
        this.repository = repository;
    }

    public void add(T entity) throws ValidatorException, RepositoryException {
        repository.save(entity);
    }

    public void delete(ID id) throws RepositoryException {
        repository.delete(id);
    }

    public void update(T entity) throws ValidatorException, RepositoryException {
        repository.update(entity);
    }

    public Set<T> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toSet());
    }
}
