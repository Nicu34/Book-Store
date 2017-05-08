package ro.bookstore.client.service;

import com.google.gson.Gson;
import ro.bookstore.client.tcp.TcpClient;
import ro.bookstore.common.domain.Book;
import ro.bookstore.common.domain.Message;
import ro.bookstore.common.service.Service;
import ro.bookstore.common.utils.MenuEnum;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Created by nicu on 5/2/2017.
 */
public class BookServiceImpl implements Service<Long, Book> {
    private Gson gson = new Gson();

    private ExecutorService executorService;

    private TcpClient tcpClient;

    public BookServiceImpl(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Book> add(Book entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.ADD_BOOK, gson.toJson(entity));
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Book.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Book> delete(Long aLong) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.DELETE_BOOK, gson.toJson(aLong));
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Book.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Book> update(Book entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.UPDATE_BOOK, gson.toJson(entity));
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Book.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Set<Book>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.GET_BOOKS, "");
            Message response = tcpClient.sendAndReceive(request);

            System.out.println(response);
            return gson.fromJson(response.getDataMessage(), Set.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Book> findOne(Long aLong) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.FIND_BOOK, gson.toJson(aLong));
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Book.class);
        }, executorService);
    }
}
