package ro.bookstore.server.repository.file.serializable;


import ro.bookstore.common.domain.Book;
import ro.bookstore.common.domain.validator.Validator;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class BookInSerializableFileRepository extends InSerializableFileRepository<Long, Book> {
    public BookInSerializableFileRepository(Validator<Book> validator, String filePath) {
        super(validator, filePath);
    }
}
