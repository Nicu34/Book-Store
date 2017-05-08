package ro.bookstore.server;

import ro.bookstore.common.BookStoreException;
import ro.bookstore.common.domain.Book;
import ro.bookstore.common.service.ClientService;
import ro.bookstore.common.service.Service;
import ro.bookstore.server.handlers.BookHandlers;
import ro.bookstore.server.handlers.ClientHandlers;
import ro.bookstore.server.handlers.SaleHandlers;
import ro.bookstore.server.repository.mysql.BookInDBRepository;
import ro.bookstore.server.repository.mysql.ClientInDBRepository;
import ro.bookstore.server.repository.mysql.SaleInDBRepository;
import ro.bookstore.server.service.BookServiceImpl;
import ro.bookstore.server.service.ClientServiceImpl;
import ro.bookstore.server.service.SaleServiceImpl;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author radu.
 */
public class ServerApp {

    private static String SERVICE_HOST = "localhost";
    private static Integer SERVICE_PORT = 1234;

    public static void main(String[] args) throws BookStoreException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ClientService clientService = new ClientServiceImpl(new ClientInDBRepository(), executorService);
        Service<Long, Book> bookService = new BookServiceImpl(new BookInDBRepository(), executorService);
        Service saleService = new SaleServiceImpl(new SaleInDBRepository(), executorService);

        ClientHandlers clientHandlers = new ClientHandlers();
        BookHandlers bookHandlers = new BookHandlers();
        SaleHandlers saleHandlers = new SaleHandlers();

        TcpServer tcpServer = new TcpServer(executorService, SERVICE_PORT);

        clientHandlers.addClientHandlers(tcpServer, clientService);
        bookHandlers.addBookSHandlers(tcpServer, bookService);
        saleHandlers.addSaleSHandlers(tcpServer, saleService);
        tcpServer.startServer();
    }
}
