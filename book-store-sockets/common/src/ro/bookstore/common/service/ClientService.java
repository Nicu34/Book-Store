package ro.bookstore.common.service;

import ro.bookstore.common.domain.Client;

import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * Created by nicu on 4/24/2017.
 */
public interface ClientService extends Service<Long, Client> {
    CompletableFuture<List<Client>> getSortedClientsByAmount();
}
