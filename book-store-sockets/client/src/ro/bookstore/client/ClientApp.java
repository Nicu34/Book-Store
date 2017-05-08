package ro.bookstore.client;

import ro.bookstore.client.service.BookServiceImpl;
import ro.bookstore.client.service.ClientServiceImpl;
import ro.bookstore.client.service.SaleServiceImpl;
import ro.bookstore.client.tcp.TcpClient;
import ro.bookstore.client.ui.Console;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ClientApp{
    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        TcpClient tcpClient = new TcpClient("localhost", 1234);
        ClientServiceImpl clientService = new ClientServiceImpl(executorService, tcpClient);
        BookServiceImpl bookService = new BookServiceImpl(executorService, tcpClient);
        SaleServiceImpl saleService = new SaleServiceImpl(executorService, tcpClient);
        Console ui = new Console(bookService, clientService, saleService);
        ui.runConsole();
        executorService.shutdownNow();
    }
}
