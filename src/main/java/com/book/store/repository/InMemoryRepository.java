package com.book.store.repository;

import com.book.store.domain.BaseEntity;
import com.book.store.domain.validator.Validator;
import com.book.store.domain.validator.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by MuresanN on 3/8/2017.
 */
public abstract class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {
    // stores the entities into a Map
    protected Map<ID, T> entities;

    protected Validator<T> validator;

    public InMemoryRepository(Validator<T> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }

    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("Invalid id. It can't be null.");
        }
        return Optional.ofNullable(entities.get(id));
    }

    @Override
    public Iterable<T> findAll() {
        return entities.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toSet());
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException, RepositoryException {
        if (entity == null) {
            throw new IllegalArgumentException("Invalid entity. It can't be null.");
        }
        validator.validate(entity);

        return Optional.ofNullable(entities.putIfAbsent(entity.getId(), entity));
    }

    @Override
    public Optional<T> delete(ID id) throws RepositoryException {
        if (id == null) {
            throw new IllegalArgumentException("Invalid id. It can't be null.");
        }

        return Optional.ofNullable(entities.remove(id));
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException, RepositoryException {
        if (entity == null) {
            throw new IllegalArgumentException("Invalid id. It can't be null.");
        }
        validator.validate(entity);

        return Optional.ofNullable(entities.computeIfPresent(entity.getId(), (k, v) -> entity));
    }
}
