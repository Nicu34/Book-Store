package ro.bookstore.server.service;

import ro.bookstore.common.domain.Book;
import ro.bookstore.server.repository.Repository;

import java.util.concurrent.ExecutorService;

/**
 * Created by nicu on 4/24/2017.
 */
public class BookServiceImpl extends ServiceImpl<Long, Book> {
    public BookServiceImpl(Repository<Long, Book> repository, ExecutorService executorService) {
        super(repository, executorService);
    }
}
