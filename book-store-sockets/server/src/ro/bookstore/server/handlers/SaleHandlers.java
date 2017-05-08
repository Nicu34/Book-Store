package ro.bookstore.server.handlers;

import com.google.gson.Gson;
import ro.bookstore.common.domain.Client;
import ro.bookstore.common.domain.Message;
import ro.bookstore.common.domain.Sale;
import ro.bookstore.common.service.Service;
import ro.bookstore.common.utils.MenuEnum;
import ro.bookstore.server.TcpServer;

import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by nicu on 5/3/2017.
 */
public class SaleHandlers {
    private Gson gson = new Gson();

    public void addSaleSHandlers(TcpServer tcpServer, Service<Long, Sale> saleService) {
        tcpServer.addHandler(MenuEnum.ADD_SALE, request -> {
            CompletableFuture<Sale> result = saleService.add(gson.fromJson(request.getDataMessage(), Sale.class));

            try {
                return new Message(MenuEnum.OK, result.get() + " was added successfully.");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "ERROR adding sale");
        });

        tcpServer.addHandler(MenuEnum.DELETE_SALE, request -> {
            CompletableFuture<Sale> result = saleService.delete(gson.fromJson(request.getDataMessage(), Long.class));

            try {
                return new Message(MenuEnum.OK, result.get() + " was removed successfully.");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "Error removing sale");
        });

        tcpServer.addHandler(MenuEnum.GET_SALES, request -> {
            CompletableFuture<Set<Sale>> result = saleService.getAll();

            try {
                return new Message(MenuEnum.OK, result.get().toString());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "Error showing sales");
        });

        tcpServer.addHandler(MenuEnum.UPDATE_SALE, request -> {
            CompletableFuture<Sale> result = saleService.update(gson.fromJson(request.getDataMessage(), Sale.class));
            try {
                return new Message(MenuEnum.OK, result.get() + " updated successfully.");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            return new Message(MenuEnum.ERROR, "Error updating sale");
        });
    }
}
