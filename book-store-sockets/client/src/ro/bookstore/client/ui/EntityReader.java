package ro.bookstore.client.ui;

import ro.bookstore.common.domain.BaseEntity;

/**
 * Created by MuresanN on 3/15/2017.
 */
@FunctionalInterface
public interface EntityReader<T extends BaseEntity> {
    T readEntity();
}
