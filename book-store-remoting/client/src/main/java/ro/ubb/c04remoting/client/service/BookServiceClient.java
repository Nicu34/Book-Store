package ro.ubb.c04remoting.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.c04remoting.common.domain.Book;
import ro.ubb.c04remoting.common.service.BookService;

import java.util.Optional;
import java.util.Set;

/**
 * Created by MuresanN on 5/8/2017.
 */
@Component
public class BookServiceClient implements BookService {

    @Autowired
    private BookService bookService;

    @Override
    public Optional<Book> add(Book entity) {
        return bookService.add(entity);
    }

    @Override
    public Optional<Book> delete(Long aLong) {
        return bookService.delete(aLong);
    }

    @Override
    public Optional<Book> update(Book entity) {
        return bookService.update(entity);
    }

    @Override
    public Set<Book> getAll() {
        return bookService.getAll();
    }

    @Override
    public Optional<Book> findOne(Long aLong) {
        return bookService.findOne(aLong);
    }
}
