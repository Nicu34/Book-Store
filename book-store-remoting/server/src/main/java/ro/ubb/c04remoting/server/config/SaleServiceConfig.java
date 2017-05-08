package ro.ubb.c04remoting.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;
import ro.ubb.c04remoting.common.domain.Sale;
import ro.ubb.c04remoting.common.service.Service;
import ro.ubb.c04remoting.server.repository.RepositoryException;
import ro.ubb.c04remoting.server.repository.mysql.SaleInDBRepository;
import ro.ubb.c04remoting.server.service.SaleServiceImpl;

/**
 * Created by nicu.
 */
@Configuration
public class SaleServiceConfig {

//    @Bean
//    public Repository<Long, Sale> bookRepository() throws RepositoryException {
//        return new SaleInDBRepository();
//    }
//
//    @Bean
//    public Service<Long, Sale> saleService(Repository<Long, Sale> repository) {
//        return new SaleServiceImpl(repository);
//    }

    @Bean
    public Service<Long, Sale> saleService() throws RepositoryException {
        return new SaleServiceImpl(new SaleInDBRepository());
    }

    @Bean
    public RmiServiceExporter rmiSaleServiceExporter() throws RepositoryException {
        RmiServiceExporter exporter = new RmiServiceExporter();

        exporter.setServiceName("SaleService");
        exporter.setServiceInterface(Service.class);
        exporter.setService(saleService());

        return exporter;
    }
}
