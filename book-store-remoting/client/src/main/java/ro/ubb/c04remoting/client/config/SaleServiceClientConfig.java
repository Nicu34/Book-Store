package ro.ubb.c04remoting.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import ro.ubb.c04remoting.common.service.SaleService;

/**
 * Created by nicu.
 */
@Configuration
@ComponentScan("ro.ubb.c04remoting.client.service")
public class SaleServiceClientConfig {

    @Bean
    RmiProxyFactoryBean rmiProxySaleFactoryBean(){
        RmiProxyFactoryBean factoryBean = new RmiProxyFactoryBean();
        factoryBean.setServiceUrl("rmi://localhost:1099/SaleService");
        factoryBean.setServiceInterface(SaleService.class);
        return factoryBean;
    }

}
