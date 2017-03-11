package com.book.store.repository;

import com.book.store.domain.Client;
import com.book.store.domain.validator.Validator;

/**
 * Created by MuresanN on 3/8/2017.
 */
public class ClientInFileRepository extends InFileRepository<Long, Client> {
    public ClientInFileRepository(Validator<Client> validator, String filePath) {
        super(validator, filePath);
    }
}
