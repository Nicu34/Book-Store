package ro.ubb.c04remoting.server.repository.file.serializable;


import ro.ubb.c04remoting.common.domain.Client;
import ro.ubb.c04remoting.common.domain.validator.Validator;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class ClientInSerializableFileRepository extends InSerializableFileRepository<Long, Client> {
    public ClientInSerializableFileRepository(Validator<Client> validator, String filePath) {
        super(validator, filePath);
    }
}
