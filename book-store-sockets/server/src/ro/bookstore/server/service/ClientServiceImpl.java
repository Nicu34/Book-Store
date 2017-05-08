package ro.bookstore.server.service;

import ro.bookstore.common.BookStoreException;
import ro.bookstore.common.domain.Client;
import ro.bookstore.common.service.ClientService;
import ro.bookstore.common.service.ServiceException;
import ro.bookstore.server.repository.Repository;
import ro.bookstore.server.repository.RepositoryException;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by nicu on 4/24/2017.
 */
public class ClientServiceImpl extends ServiceImpl<Long, Client> implements ClientService {
    public ClientServiceImpl(Repository<Long, Client> repository, ExecutorService executorService) {
        super(repository, executorService);
    }

    @Override
    public CompletableFuture<List<Client>> getSortedClientsByAmount() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return StreamSupport.stream(repository.findAll().spliterator(), false)
                .sorted(Comparator.comparingLong(Client::getMoneyAmount))
                .collect(Collectors.toList());
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }, executorService);
    }
}
