package ro.bookstore.common.domain.validator;

import ro.bookstore.common.domain.Sale;
import ro.bookstore.common.utils.ValidatorUtils;

/**
 * Created by Ioana on 3/14/2017.
 */
public class SaleValidator implements Validator<Sale> {
    @Override
    public void validate(Sale entity) throws ValidatorException {
        String message = "";

        if (!ValidatorUtils.isValidPositiveNumber(entity.getId())) {
            message += "Invalid id " + entity.getId() + " inserted. \n";
        }
        if (!ValidatorUtils.isValidPositiveNumber(entity.getClientId())) {
            message += "Invalid client id " + entity.getClientId() + " inserted. \n";
        }
        if (!ValidatorUtils.isValidPositiveNumber(entity.getBookId())) {
            message += "Invalid book id " + entity.getBookId() + " inserted. \n";
        }
        if (message.length() > 0) {
            throw new ValidatorException(message);
        }
    }
}
