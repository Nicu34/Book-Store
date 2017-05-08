package ro.bookstore.server.repository.file.serializable;


import ro.bookstore.common.domain.BaseEntity;
import ro.bookstore.common.domain.validator.Validator;
import ro.bookstore.common.utils.SerializationUtil;
import ro.bookstore.server.repository.RepositoryException;
import ro.bookstore.server.repository.file.InFileRepository;

import java.io.IOException;
import java.util.Map;

/**
 * Created by nicu on 3/28/2017.
 */
public class InSerializableFileRepository<ID, T extends BaseEntity<ID>> extends InFileRepository<ID, T> {
    public InSerializableFileRepository(Validator<T> validator, String filePath) {
        super(validator, filePath);
    }

    /**
     * Loads from file with given path the given entities.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void loadFromFile() throws RepositoryException {
        try {
            entities = ((Map) SerializationUtil.deserialize(filePath));
        }
        catch (IOException | ClassNotFoundException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    /**
     * Saves to file with given path the given entities
     * @throws IOException
     */
    public void saveToFile() throws RepositoryException {
        try {
            SerializationUtil.serialize(entities, filePath);
        }
        catch (IOException e) {
            throw new RepositoryException(e.getMessage());
        }
    }
}
