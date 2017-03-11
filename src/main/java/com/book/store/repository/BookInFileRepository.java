package com.book.store.repository;

import com.book.store.domain.Book;
import com.book.store.domain.validator.Validator;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class BookInFileRepository extends InFileRepository<Long, Book> {
    public BookInFileRepository(Validator<Book> validator, String filePath) {
        super(validator, filePath);
    }
}
