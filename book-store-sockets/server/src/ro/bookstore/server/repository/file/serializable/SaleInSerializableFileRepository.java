package ro.bookstore.server.repository.file.serializable;

import ro.bookstore.common.domain.Sale;
import ro.bookstore.common.domain.validator.Validator;

/**
 * Created by Ioana on 3/14/2017.
 */
public class SaleInSerializableFileRepository extends InSerializableFileRepository<Long, Sale> {

    public SaleInSerializableFileRepository(Validator<Sale> validator, String filePath) {
        super(validator, filePath);
    }
}
