package ro.ubb.c04remoting.server.repository.file.serializable;


import ro.ubb.c04remoting.common.domain.Book;
import ro.ubb.c04remoting.common.domain.validator.Validator;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class BookInSerializableFileRepository extends InSerializableFileRepository<Long, Book> {
    public BookInSerializableFileRepository(Validator<Book> validator, String filePath) {
        super(validator, filePath);
    }
}
