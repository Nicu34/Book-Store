package ro.ubb.c04remoting.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.c04remoting.common.domain.Client;
import ro.ubb.c04remoting.common.service.ClientService;

import java.util.Optional;
import java.util.Set;

/**
 * Created by MuresanN on 5/8/2017.
 */
@Component
public class ClientServiceClient implements ClientService {

    @Autowired
    private ClientService clientService;

    @Override
    public Optional<Client> add(Client entity) {
        return clientService.add(entity);
    }

    @Override
    public Optional<Client> delete(Long aLong) {
        return clientService.delete(aLong);
    }

    @Override
    public Optional<Client> update(Client entity) {
        return clientService.update(entity);
    }

    @Override
    public Set<Client> getAll() {
        return clientService.getAll();
    }

    @Override
    public Optional<Client> findOne(Long aLong) {
        return clientService.findOne(aLong);
    }
}
