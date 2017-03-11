package com.book.store;

import com.book.store.domain.validator.BookValidator;
import com.book.store.domain.validator.ClientValidator;
import com.book.store.repository.BookInFileRepository;
import com.book.store.repository.ClientInFileRepository;
import com.book.store.service.BookService;
import com.book.store.service.ClientService;
import com.book.store.ui.Console;

import java.io.IOException;

public class Main {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        ClientInFileRepository clientRepository = new ClientInFileRepository(new ClientValidator(), "clientsData.ser");
        BookInFileRepository bookRepository = new BookInFileRepository(new BookValidator(), "booksData.ser");

        clientRepository.loadFromFile();
        bookRepository.loadFromFile();

        ClientService clientService = new ClientService(clientRepository);
        BookService bookService = new BookService(bookRepository);

        Console console = new Console(bookService, clientService);
        console.runConsole();

    }
}
