package ro.ubb.c04remoting.server.service;

import ro.ubb.c04remoting.common.domain.Client;
import ro.ubb.c04remoting.server.repository.Repository;

/**
 * Created by nicu on 4/24/2017.
 */
public class ClientServiceImpl extends ServiceImpl<Long, Client> {
    public ClientServiceImpl(Repository<Long, Client> repository) {
        super(repository);
    }
}
