package ro.bookstore.common.domain.validator;

import ro.bookstore.common.domain.Client;
import ro.bookstore.common.utils.ValidatorUtils;

/**
 * Created by nicu on 3/8/2017.
 */
public class ClientValidator implements Validator<Client> {
    @Override
    public void validate(Client entity) throws ValidatorException {
        String message = "";

        if (!ValidatorUtils.isValidPositiveNumber(entity.getId())) {
            message += "Invalid id " + entity.getId() + " inserted. \n";
        }
        if (!ValidatorUtils.isValidName(entity.getName())) {
            message += "Invalid name " + entity.getName() + " inserted. \n";
        }
        if (!ValidatorUtils.isValidPositiveNumber(entity.getMoneyAmount())) {
            message += "Invalid money amount " + entity.getMoneyAmount() + " inserted. \n";
        }

        if (message.length() > 0) {
            throw new ValidatorException(message);
        }
    }
}
