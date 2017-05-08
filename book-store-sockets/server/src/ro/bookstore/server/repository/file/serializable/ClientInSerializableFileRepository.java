package ro.bookstore.server.repository.file.serializable;


import ro.bookstore.common.domain.Client;
import ro.bookstore.common.domain.validator.Validator;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class ClientInSerializableFileRepository extends InSerializableFileRepository<Long, Client> {
    public ClientInSerializableFileRepository(Validator<Client> validator, String filePath) {
        super(validator, filePath);
    }
}
