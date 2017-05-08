package ro.bookstore.client.service;

import com.google.gson.Gson;
import ro.bookstore.client.tcp.TcpClient;
import ro.bookstore.common.domain.Sale;
import ro.bookstore.common.domain.Message;
import ro.bookstore.common.domain.Sale;
import ro.bookstore.common.service.Service;
import ro.bookstore.common.utils.MenuEnum;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

/**
 * Created by nicu on 5/3/2017.
 */
public class SaleServiceImpl implements Service<Long, Sale> {
    private Gson gson = new Gson();

    private ExecutorService executorService;

    private TcpClient tcpClient;

    public SaleServiceImpl(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }

    @Override
    public CompletableFuture<Sale> add(Sale entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.ADD_SALE, gson.toJson(entity));
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Sale.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Sale> delete(Long aLong) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.DELETE_SALE, gson.toJson(aLong));
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Sale.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Sale> update(Sale entity) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.UPDATE_SALE, gson.toJson(entity));
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Sale.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Set<Sale>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.GET_SALES, "");
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Set.class);
        }, executorService);
    }

    @Override
    public CompletableFuture<Sale> findOne(Long aLong) {
        return CompletableFuture.supplyAsync(() -> {
            Message request = new Message(MenuEnum.FIND_SALE, gson.toJson(aLong));
            Message response = tcpClient.sendAndReceive(request);

            return gson.fromJson(response.getDataMessage(), Sale.class);
        }, executorService);
    }
}
