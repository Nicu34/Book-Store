package com.book.store.service;

import com.book.store.domain.Client;
import com.book.store.repository.Repository;

/**
 * Created by nicu on 3/8/2017.
 */
public class ClientService extends Service<Long, Client> {
    public ClientService(Repository<Long, Client> repository) {
        super(repository);
    }
}
