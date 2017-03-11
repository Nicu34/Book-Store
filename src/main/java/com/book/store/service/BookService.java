package com.book.store.service;

import com.book.store.domain.Book;
import com.book.store.repository.Repository;

/**
 * Created by nicu on 3/8/2017.
 */
public class BookService extends Service<Long, Book> {
    public BookService(Repository<Long, Book> repository) {
        super(repository);
    }
}
