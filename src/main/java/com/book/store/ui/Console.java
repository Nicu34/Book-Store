package com.book.store.ui;

import com.book.store.domain.Book;
import com.book.store.domain.Client;
import com.book.store.domain.validator.ValidatorException;
import com.book.store.repository.RepositoryException;
import com.book.store.service.BookService;
import com.book.store.service.ClientService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * @author nicu
 */
public class Console {
    private BookService bookService;
    private ClientService clientService;

    public Console(BookService bookService, ClientService clientService) {
        this.bookService = bookService;
        this.clientService = clientService;
    }

    /**
     * Runs hardcoded CRUD and filter operations.
     */
    public void runConsole() {
        printAllBooks();
        printAllClients();
        System.out.println("Add clients: ");
        addClients();
        System.out.println("Add books: ");
        addBooks();
        printAllBooks();
        printAllClients();
        updateBook();
        updateClient();
        printAllBooks();
        printAllClients();
        deleteBook();
        deleteClient();
        printAllBooks();
        printAllClients();
        filterBooks();
        filterClients();
    }

    /**
     * Filters the books with the given name.
     */
    private void filterBooks() {
        System.out.println("Read name of the book");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name = bufferRead.readLine();
            bookService.getAll().stream().filter(book -> book.getName().equals(name)).collect(Collectors.toList()).forEach(System.out::println);
        }
        catch (IOException e) {
            System.out.println("Unknown io exception "  + e.getMessage());
        }
    }

    /**
     * Filters the clients with given name.
     */
    private void filterClients() {
        System.out.println("Read name of the client");
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            String name = bufferRead.readLine();
            clientService.getAll().stream().filter(client -> client.getName().equals(name)).collect(Collectors.toList()).forEach(System.out::println);
        }
        catch (IOException e) {
            System.out.println("Unknown io exception "  + e.getMessage());
        }
    }

    /**
     * Pritns all the books
     */
    private void printAllBooks() {
        bookService.getAll().forEach(System.out::println);
    }

    /**
     * Prints all the clients
     */
    private void printAllClients() {
        clientService.getAll().forEach(System.out::println);
    }

    /**
     * Adds a book to file.
     */
    private void addBooks() {
        Integer counter = 0;
        while (counter < 1) {
            counter++;
            Book book = readBook();
            if (book == null || book.getId() < 0) {
                break;
            }
            try {
                bookService.add(book);
            }
            catch (ValidatorException e) {
                e.getStackTrace();
            }
            catch (RepositoryException e) {
                e.getStackTrace();
            }
        }
    }

    /**
     * Updates a book
     */
    private void updateBook() {
        Book book = readBook();
        if (book == null || book.getId() < 0) {
            System.out.println("Invalid book read.");
        }
        try {
            bookService.update(book);
        }
        catch (ValidatorException e) {
            e.getStackTrace();
        }
        catch (RepositoryException e) {
            e.getStackTrace();
        }
    }

    /**
     * Deletes a book.
     */
    private void deleteBook() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            bookService.delete(id);
        }
        catch (IOException e) {
            e.getStackTrace();
        }
        catch (RepositoryException e) {
            e.getStackTrace();
        }
    }


    /**
     * Add clients
     */
    private void addClients() {
        Integer counter = 0;
        while (counter < 1) {
            Client client = readClient();
            counter++;
            if (client == null || client.getId() < 0) {
                break;
            }
            try {
                clientService.add(client);
            }
            catch (ValidatorException e) {
                e.getStackTrace();
            }
            catch (RepositoryException e) {
                e.getStackTrace();
            }
        }
    }

    /**
     * Updates client.
     */
    private void updateClient() {
        Client client = readClient();
        if (client == null || client.getId() < 0) {
            System.out.println("Invalid client read.");
        }
        try {
            clientService.update(client);
        }
        catch (ValidatorException e) {
            e.getStackTrace();
        }
        catch (RepositoryException e) {
            e.getStackTrace();
        }
    }

    /**
     * Delete clients.
     */
    private void deleteClient() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            clientService.delete(id);
        }
        catch (IOException e) {
            e.getStackTrace();
        }
        catch (RepositoryException e) {
            e.getStackTrace();
        }
    }

    /**
     * Reads a book.
     * @return Book
     */
    private Book readBook() {
        System.out.println("Read book {id, name, writer, price, quantity}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            String writer = bufferRead.readLine();
            Long price = Long.valueOf(bufferRead.readLine());
            Integer quantity = Integer.valueOf(bufferRead.readLine());

            Book book = new Book(id, name, writer, price, quantity);

            return book;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * reads a client
     * @return Client
     */
    private Client readClient() {
        System.out.println("Read client {id, name, moneyAmount}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            Long id = Long.valueOf(bufferRead.readLine());
            String name = bufferRead.readLine();
            Long moneyAmount = Long.valueOf(bufferRead.readLine());

            Client client = new Client(id, name, moneyAmount);

            return client;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
