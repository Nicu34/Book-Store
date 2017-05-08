package ro.ubb.c04remoting.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.c04remoting.common.domain.Client;
import ro.ubb.c04remoting.common.service.Service;
import ro.ubb.c04remoting.server.repository.RepositoryException;
import ro.ubb.c04remoting.server.repository.mysql.ClientInDBRepository;
import ro.ubb.c04remoting.server.service.ClientServiceImpl;

/**
 * Created by nicu.
 */
@Configuration
public class ClientServiceConfig {

//    @Bean
//    public Repository<Long, Client> clientRepository() throws RepositoryException {
//        return new ClientInDBRepository();
//    }
//
//    @Bean
//    public Service<Long, Client> clientService(Repository<Long, Client> repository) {
//        return new ClientServiceImpl(repository);
//    }

    @Bean
    public Service<Long, Client> clientService() throws RepositoryException {
        return new ClientServiceImpl(new ClientInDBRepository());
    }

    @Bean
    public RmiServiceExporter rmiClientServiceExporter() throws RepositoryException {
        RmiServiceExporter exporter = new RmiServiceExporter();

        exporter.setServiceName("ClientService");
        exporter.setServiceInterface(Service.class);
        exporter.setService(clientService());

        return exporter;
    }
}
