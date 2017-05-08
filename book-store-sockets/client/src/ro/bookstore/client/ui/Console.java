package ro.bookstore.client.ui;

import ro.bookstore.client.service.BookServiceImpl;
import ro.bookstore.client.service.ClientServiceImpl;
import ro.bookstore.client.service.SaleServiceImpl;
import ro.bookstore.common.domain.BaseEntity;
import ro.bookstore.common.domain.Book;
import ro.bookstore.common.domain.Client;
import ro.bookstore.common.domain.Sale;
import ro.bookstore.common.service.Service;
import ro.bookstore.common.utils.SalesIdGenerator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

/**
 * @author nicu
 */
public class Console {
    private BookServiceImpl bookService;
    private ClientServiceImpl clientService;
    private SaleServiceImpl saleService;

    public Console(BookServiceImpl bookService, ClientServiceImpl clientService, SaleServiceImpl saleService) {
        this.bookService = bookService;
        this.clientService = clientService;
        this.saleService = saleService;
    }

    /**
     * Methodsd runs the console where the user will input the data by a command.
     *
     * @throws IOException
     */
    public void runConsole() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            Boolean exit = false;
            System.out.println("\n#############   Book Store Application ############# \n");
//            System.out.println("\nList of sales: ");
//            printEntity(saleService);
            System.out.println(getConsoleMenu());
            System.out.println("Command = ");
            String command = bufferedReader.readLine();

            switch (command) {
                case "1":
                    addEntity(bookService, new Book(), Console::readBook);
                    break;
                case "2":
                    addEntity(clientService, new Client(), Console::readClient);
                    break;
                case "3":
                    updateEntity(bookService, new Book(), Console::readBook);
                    break;
                case "4":
                    updateEntity(clientService, new Client(), Console::readClient);
                    break;
                case "5":
                    deleteEntity(bookService);
                    break;
                case "6":
                    deleteEntity(clientService);
                    break;
                case "7":
                    filterBooks();
                    break;
                case "8":
                    filterClients();
                    break;
                case "9":
                    sortClients();
                    break;
                case "10":
                    buyBook();
                    break;
                case "11":
                    System.out.println("\nList of books: ");
                    printEntity(bookService);
                    break;
                case "12":
                    System.out.println("\nList of clients: ");
                    printEntity(clientService);
                    break;
                case "13":
                    System.out.println("\nList of sales: ");
                    printEntity(saleService);
                    break;
                default:
                    exit = true;
                    break;
            }

            if (exit) {
                System.out.println("Goodbye! \n");
                break;
            }
        }
    }

    /**
     * Creates the menu for the app.
     *
     * @return menu Strig which contains a menu
     */
    private String getConsoleMenu() {
        String menu = "\nList of available commands: \n";

        menu += "0. Exit. \n";
        menu += "1. Add book. \n";
        menu += "2. Add client. \n";
        menu += "3. Update book. \n";
        menu += "4. Update client. \n";
        menu += "5. Delete book. \n";
        menu += "6. Delete client. \n";
        menu += "7. Filter books by name. \n";
        menu += "8. Filter clients by name. \n";
        menu += "9. Sort clients base on spent amount of money. \n";
        menu += "10. Buy books. \n";
        menu += "11. Show list of books. \n";
        menu += "12. Show list of clients. \n";
        menu += "13. Show list of sales. \n";

        return menu;
    }

    /**
     * Filters the books with the given name.
     */
    private void filterBooks() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Book Name = ");
            String name = bufferRead.readLine();
            bookService.getAll().thenAccept(books -> books
                    .stream()
                    .filter(book -> book.getName().equals(name))
                    .collect(Collectors.toList())
                    .forEach(System.out::println));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Filters the clients with given name.
     */
    private void filterClients() {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Client Name = ");
            String name = bufferRead.readLine();
            clientService.getAll().thenAccept(clients -> clients
                    .stream()
                    .filter(client -> client.getName().equals(name))
                    .collect(Collectors.toList())
                    .forEach(System.out::println));

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Sorts clients based on the spent amount of money
     */
    private void sortClients() {
        System.out.println("Sorting clients based on the amount of money");
        clientService.getSortedClientsByAmount().thenAccept(clients -> clients.forEach(System.out::println));
    }

    /**
     * Prints on screen the entities of a given service
     *
     * @param service given service
     */
    private void printEntity(Service service) {
        service.getAll().thenAccept(o -> ((List) o).stream().forEach(System.out::println));
    }

    /**
     * Deletes an entity from a service based on given ID.
     */
    private void deleteEntity(Service service) {
        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Id = ");
            Long id = Long.valueOf(bufferRead.readLine());
            service.delete(id);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds an entity
     *
     * @param service      given service
     * @param baseEntity   given entity
     * @param entityReader given entity reader method given by a functional interface
     */
    private void addEntity(Service service, BaseEntity baseEntity, EntityReader entityReader) {
        baseEntity = entityReader.readEntity();
        if (baseEntity != null) {
            service.add(baseEntity);
            System.out.println("Console " + baseEntity);
        }
    }

    /**
     * Updates an entity
     *
     * @param service      given service
     * @param baseEntity   given entity
     * @param entityReader given entity reader method given by a functional interface
     */
    private void updateEntity(Service service, BaseEntity baseEntity, EntityReader entityReader) {
        baseEntity = entityReader.readEntity();
        if (baseEntity != null) {
            service.update(baseEntity);
        }
    }

    /**
     * Reads a book.
     *
     * @return Book
     */
    private static Book readBook() {
        System.out.println("Read book {id, name, writer, price, quantity}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Id = ");
            Long id = Long.valueOf(bufferRead.readLine());
            System.out.print("Name = ");
            String name = bufferRead.readLine();
            System.out.print("Writer = ");
            String writer = bufferRead.readLine();
            System.out.print("Price = ");
            Long price = Long.valueOf(bufferRead.readLine());
            System.out.print("Quantity = ");
            Integer quantity = Integer.valueOf(bufferRead.readLine());

            return new Book(id, name, writer, price, quantity);
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * reads a client
     *
     * @return Client
     */
    private static Client readClient() {
        System.out.println("Read client {id, name, moneyAmount}");

        BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Id = ");
            Long id = Long.valueOf(bufferRead.readLine());
            System.out.print("Name = ");
            String name = bufferRead.readLine();
            System.out.print("Money Amount = ");
            Long moneyAmount = Long.valueOf(bufferRead.readLine());

            return new Client(id, name, moneyAmount);
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * buys a book and maps the book id the the client id
     */
    private void buyBook() {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Give the ID of the client:");
            Long clientId = Long.valueOf(bufferedReader.readLine());

            System.out.println("Give the ID of the book you want to buy:");
            Long bookId = Long.valueOf(bufferedReader.readLine());

            Optional<Client> clientOptional = Optional.ofNullable(clientService.findOne(clientId).get());
            Optional<Book> bookOptional = Optional.ofNullable(bookService.findOne(bookId).get());
            if (!clientOptional.isPresent()) {
                throw new ConsoleException("Client with id " + clientId + " doesn't exists.");
            }
            if (!bookOptional.isPresent()) {
                throw new ConsoleException("Book with id " + bookId + " doesn't exists.");
            }

            Book book = bookOptional.get();
            Client client = clientOptional.get();

            if (book.getQuantity().equals(0)) {
                throw new ConsoleException("Quantity zero for " + book);
            }
            if (book.getPrice() > client.getMoneyAmount()) {
                throw new ConsoleException("Insufficient money amount for " + client + " to buy " + book);
            }

            addNewSale(clientId, bookId);
            book.setQuantity(book.getQuantity() - 1);
            client.setMoneyAmount(client.getMoneyAmount() - book.getPrice());
            bookService.update(book);
            clientService.update(client);
            System.out.println("Successfully bought!");
        } catch (IOException | ConsoleException | InterruptedException | ExecutionException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * adds a new sale
     *
     * @param clientId the client id given as Long
     * @param bookId   the book id given as Long
     */
    private void addNewSale(Long clientId, Long bookId) {
        Sale sale = new Sale(SalesIdGenerator.getId());

        sale.setClientId(clientId);
        sale.setBookId(bookId);
        saleService.add(sale);
        System.out.println(sale.toString());
    }
}
