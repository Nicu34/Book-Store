package ro.bookstore.server.handlers;

import com.google.gson.Gson;
import ro.bookstore.common.domain.Book;
import ro.bookstore.common.domain.Client;
import ro.bookstore.common.domain.Message;
import ro.bookstore.common.service.ClientService;
import ro.bookstore.common.service.Service;
import ro.bookstore.common.utils.MenuEnum;
import ro.bookstore.server.TcpServer;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

/**
 * Created by nicu on 4/24/2017.
 */
public class ClientHandlers {
    private Gson gson = new Gson();

    public void addClientHandlers(TcpServer tcpServer, ClientService clientService) {
        tcpServer.addHandler(MenuEnum.ADD_CLIENT, request -> {
            CompletableFuture<Client> result = clientService.add(gson.fromJson(request.getDataMessage(), Client.class));

            try {
                return new Message(MenuEnum.OK, result.get() + " was added successfully.");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "ERROR adding client");
        });

        tcpServer.addHandler(MenuEnum.DELETE_CLIENT, request -> {
            CompletableFuture<Client> result = clientService.delete(gson.fromJson(request.getDataMessage(), Long.class));

            try {
                return new Message(MenuEnum.OK, result.get() + " was removed successfully.");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "Error removing client");
        });

        tcpServer.addHandler(MenuEnum.GET_CLIENTS, request -> {
            CompletableFuture<Set<Client>> result = clientService.getAll();

            try {
                return new Message(MenuEnum.OK, result.get().toString());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "Error showing clients");
        });

        tcpServer.addHandler(MenuEnum.UPDATE_CLIENT, request -> {
            CompletableFuture<Client> result = clientService.update(gson.fromJson(request.getDataMessage(), Client.class));
            try {
                return new Message(MenuEnum.OK, result.get() + " updated successfully.");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "Error updating client");
        });

        tcpServer.addHandler(MenuEnum.FIND_CLIENT, request -> {
            CompletableFuture<Client> result = clientService.findOne(gson.fromJson(request.getDataMessage(), Long.class));
            try {
                return new Message(MenuEnum.OK, gson.toJson(result.get()));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "Error finding client");
        });

        tcpServer.addHandler(MenuEnum.SORT_CLIENTS, request -> {
            try {
                CompletableFuture<List<Client>> result = clientService.getSortedClientsByAmount();
                return new Message(MenuEnum.OK, result.get().toString());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "Error showing clients sorted.");
        });
    }
}
