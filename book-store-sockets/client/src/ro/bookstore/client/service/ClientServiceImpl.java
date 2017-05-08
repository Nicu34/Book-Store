package ro.bookstore.client.service;

import com.google.gson.Gson;
import ro.bookstore.client.tcp.TcpClient;
import ro.bookstore.common.domain.Client;
import ro.bookstore.common.domain.Message;
import ro.bookstore.common.service.ClientService;
import ro.bookstore.common.utils.MenuEnum;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Created by nicu on 5/2/2017.
 */
public class ClientServiceImpl implements ClientService {

    private Gson gson = new Gson();

    private ExecutorService executorService;

    private TcpClient tcpClient;

    public ClientServiceImpl(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<List<Client>> getSortedClientsByAmount() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.SORT_CLIENTS, "");
            Message response = tcpClient.sendAndReceive(request);

           return gson.fromJson(response.getDataMessage(), List.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Client> add(Client entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.ADD_CLIENT, gson.toJson(entity));
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Client.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Client> delete(Long aLong) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.DELETE_CLIENT, gson.toJson(aLong));
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Client.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Client> update(Client entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.UPDATE_CLIENT, gson.toJson(entity));
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Client.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Set<Client>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.GET_CLIENTS, "");
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Set.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Client> findOne(Long aLong) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.FIND_CLIENT, gson.toJson(aLong));
            System.out.println("CE REQUEST " + request);
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Client.class);
        }, executorService);
    }
}