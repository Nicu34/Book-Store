package ro.bookstore.common.domain.validator;

import ro.bookstore.common.domain.Book;
import ro.bookstore.common.utils.ValidatorUtils;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class BookValidator implements Validator<Book> {

    @Override
    public void validate(Book book) throws ValidatorException {
        String message = "";

        if (!ValidatorUtils.isValidPositiveNumber(book.getId())) {
            message += "Invalid id inserted. \n";
        }
        if (!ValidatorUtils.isValidName(book.getName())) {
            message += "Invalid name " + book.getName() + " inserted. \n";
        }
        if (!ValidatorUtils.isValidName(book.getWriter())) {
            message += "Invalid writer " + book.getWriter() + " inserted. \n";
        }
        if (!ValidatorUtils.isValidPositiveNumber(book.getQuantity())) {
            message += "Invalid quantity " + book.getQuantity() + " inserted. \n";
        }
        if (!ValidatorUtils.isValidPositiveNumber(book.getPrice())) {
            message += "Invalid price " + book.getPrice() + "inserted. \n";
        }

        if (message.length() > 0) {
            throw new ValidatorException(message);
        }
    }
}
