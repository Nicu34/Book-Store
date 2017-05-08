package ro.ubb.c04remoting.server.service;

import ro.ubb.c04remoting.common.domain.Book;
import ro.ubb.c04remoting.server.repository.Repository;

/**
 * Created by nicu on 4/24/2017.
 */
public class BookServiceImpl extends ServiceImpl<Long, Book> {
    public BookServiceImpl(Repository<Long, Book> repository) {
        super(repository);
    }
}
