package ro.bookstore.server.handlers;

import com.google.gson.Gson;
import ro.bookstore.common.domain.Book;
import ro.bookstore.common.domain.Client;
import ro.bookstore.common.domain.Message;
import ro.bookstore.common.service.Service;
import ro.bookstore.common.utils.MenuEnum;
import ro.bookstore.server.TcpServer;
import ro.bookstore.server.service.BookServiceImpl;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;

/**
 * Created by nicu on 4/24/2017.
 */
public class BookHandlers {
    private Gson gson = new Gson();

    public void addBookSHandlers(TcpServer tcpServer, Service<Long, Book> bookService) {
        tcpServer.addHandler(MenuEnum.ADD_BOOK, request -> {
            CompletableFuture<Book> result = bookService.add(gson.fromJson(request.getDataMessage(), Book.class));

            try {
                return new Message(MenuEnum.OK, result.get() + " was added successfully.");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "ERROR adding book");
        });

        tcpServer.addHandler(MenuEnum.DELETE_BOOK, request -> {
            CompletableFuture<Book> result = bookService.delete(gson.fromJson(request.getDataMessage(), Long.class));

            try {
                return new Message(MenuEnum.OK, result.get() + " was removed successfully.");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "Error removing book");
        });

        tcpServer.addHandler(MenuEnum.GET_BOOKS, request -> {
            CompletableFuture<Set<Book>> result = bookService.getAll();

            try {
                return new Message(MenuEnum.OK, result.get().toString());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "Error showing books");
        });

        tcpServer.addHandler(MenuEnum.UPDATE_BOOK, request -> {
            CompletableFuture<Book> result = bookService.update(gson.fromJson(request.getDataMessage(), Book.class));
            try {
                return new Message(MenuEnum.OK, result.get() + " updated successfully.");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "Error updating book");
        });

        tcpServer.addHandler(MenuEnum.FIND_BOOK, request -> {
            CompletableFuture<Book> result = bookService.findOne(gson.fromJson(request.getDataMessage(), Long.class));
            try {
                return new Message(MenuEnum.OK, gson.toJson(result.get()));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "Error finding book");
        });
    }
}
