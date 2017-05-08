package ro.bookstore.server.repository.file;

import ro.bookstore.common.domain.BaseEntity;
import ro.bookstore.common.domain.validator.Validator;
import ro.bookstore.common.domain.validator.ValidatorException;
import ro.bookstore.server.repository.InMemoryRepository;
import ro.bookstore.server.repository.RepositoryException;

import java.io.IOException;
import java.util.Optional;

/**
 * Created by MuresanN on 3/8/2017.
 */
public abstract class InFileRepository<ID, T extends BaseEntity<ID>> extends InMemoryRepository<ID, T> {
    protected String filePath;

    public InFileRepository(Validator<T> validator, String filePath) {
        super(validator);
        this.filePath = filePath;
    }

    /**
     * Loads from file with given path the given entities.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public abstract void loadFromFile() throws RepositoryException;

    /**
     * Saves to file with given path the given entities
     *
     * @throws IOException
     */
    public abstract void saveToFile() throws RepositoryException;

    @Override
    public Optional<T> save(T entity) throws ValidatorException, RepositoryException {
        if (entity == null) {
            throw new IllegalArgumentException("Invalid entity. It can't be null.");
        }
        validator.validate(entity);

        Optional<T> optional = super.save(entity);
        saveToFile();

        return optional;
    }

    @Override
    public Optional<T> delete(ID id) throws RepositoryException {
        if (id == null) {
            throw new IllegalArgumentException("Invalid id. It can't be null.");
        }

        Optional<T> optional = super.delete(id);
        saveToFile();

        return optional;
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException, RepositoryException {
        if (entity == null) {
            throw new IllegalArgumentException("Invalid id. It can't be null.");
        }
        validator.validate(entity);

//        super.delete(entity.getId());
//        Optional<T> optional = super.save(entity);

        Optional<T> optional = super.update(entity);
        saveToFile();
        return optional;
    }
}
