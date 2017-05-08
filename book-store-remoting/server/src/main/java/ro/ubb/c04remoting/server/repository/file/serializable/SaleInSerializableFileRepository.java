package ro.ubb.c04remoting.server.repository.file.serializable;

import ro.ubb.c04remoting.common.domain.Sale;
import ro.ubb.c04remoting.common.domain.validator.Validator;

/**
 * Created by Ioana on 3/14/2017.
 */
public class SaleInSerializableFileRepository extends InSerializableFileRepository<Long, Sale> {

    public SaleInSerializableFileRepository(Validator<Sale> validator, String filePath) {
        super(validator, filePath);
    }
}
