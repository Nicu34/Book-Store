package com.book.store.repository;

import com.book.store.domain.BaseEntity;
import com.book.store.domain.validator.Validator;
import com.book.store.domain.validator.ValidatorException;
import com.book.store.utils.SerializationUtil;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * Created by MuresanN on 3/8/2017.
 */
public abstract class InFileRepository<ID, T extends BaseEntity<ID>> extends InMemoryRepository<ID, T> {
    private String filePath;

    public InFileRepository(Validator<T> validator, String filePath) {
        super(validator);
        this.filePath = filePath;
    }

    /**
     * Loads from file with given path the given entities.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadFromFile() throws IOException, ClassNotFoundException {
        entities = ((Map) SerializationUtil.deserialize(filePath));
    }

    /**
     * Saves to file with given path the given entities
     * @throws IOException
     */
    private void saveToFile() throws IOException {
        SerializationUtil.serialize(entities, filePath);
    }

    @Override
    public Optional<T> save(T entity) throws ValidatorException, RepositoryException {
        if (entity == null) {
            throw new IllegalArgumentException("Invalid entity. It can't be null.");
        }
        validator.validate(entity);

        super.save(entity);
        try {
            saveToFile();
        }
        catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<T> delete(ID id) throws RepositoryException {
        if (id == null) {
            throw new IllegalArgumentException("Invalid id. It can't be null.");
        }

        super.delete(id);
        try {
            saveToFile();
        }
        catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
        return null;
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException, RepositoryException {
        if (entity == null) {
            throw new IllegalArgumentException("Invalid id. It can't be null.");
        }
        validator.validate(entity);

        super.update(entity);
        try {
            saveToFile();
        }
        catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
        return null;
    }
}
