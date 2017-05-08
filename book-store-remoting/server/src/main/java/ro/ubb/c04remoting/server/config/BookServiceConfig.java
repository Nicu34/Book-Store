package ro.ubb.c04remoting.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.c04remoting.common.domain.Book;
import ro.ubb.c04remoting.common.service.Service;
import ro.ubb.c04remoting.server.repository.RepositoryException;
import ro.ubb.c04remoting.server.repository.mysql.BookInDBRepository;
import ro.ubb.c04remoting.server.service.BookServiceImpl;

/**
 * Created by nicu.
 */
@Configuration
public class BookServiceConfig {

//    @Bean
//    public Repository<Long, Book> bookRepository() throws RepositoryException {
//        return new BookInDBRepository();
//    }

//    @Bean
//    public Service<Long, Book> bookService(Repository<Long, Book> repository) {
//        return new BookServiceImpl(repository);
//    }

    @Bean
    public Service<Long, Book> bookService() throws RepositoryException {
        return new BookServiceImpl(new BookInDBRepository());
    }

    @Bean
    public RmiServiceExporter rmiBookServiceExporter() throws RepositoryException {
        RmiServiceExporter exporter = new RmiServiceExporter();

        exporter.setServiceName("BookService");
        exporter.setServiceInterface(Service.class);
        exporter.setService(bookService());

        return exporter;
    }
}
