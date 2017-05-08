package ro.bookstore.common.service;

import ro.bookstore.common.domain.BaseEntity;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

/**
 * Created by nicu on 4/24/2017.
 */

public interface Service<ID, T extends BaseEntity<ID>> {
    CompletableFuture<T> add(T entity);

    CompletableFuture<T> delete(ID id);

    CompletableFuture<T> update(T entity);

    CompletableFuture<Set<T>> getAll();

    CompletableFuture<T> findOne(ID id);
}
