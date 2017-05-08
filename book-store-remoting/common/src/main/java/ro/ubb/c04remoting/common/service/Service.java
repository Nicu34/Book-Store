package ro.ubb.c04remoting.common.service;

import ro.ubb.c04remoting.common.domain.BaseEntity;

import java.util.Optional;
import java.util.Set;

/**
 * Created by nicu on 4/24/2017.
 */

public interface Service<ID, T extends BaseEntity<ID>> {
    Optional<T> add(T entity);

    Optional<T> delete(ID id);

    Optional<T> update(T entity);

    Set<T> getAll();

    Optional<T> findOne(ID id);
}
