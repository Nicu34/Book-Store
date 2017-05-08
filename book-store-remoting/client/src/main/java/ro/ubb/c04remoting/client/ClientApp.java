package ro.ubb.c04remoting.client;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ro.ubb.c04remoting.client.service.BookServiceClient;
import ro.ubb.c04remoting.client.service.ClientServiceClient;
import ro.ubb.c04remoting.client.service.SaleServiceClient;

/**
 * Created by nicu.
 */
public class ClientApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(
                "ro.ubb.c04remoting.client" +
                ".config");

        BookServiceClient bookServiceClient = context.getBean(BookServiceClient.class);
        SaleServiceClient saleServiceClient = context.getBean(SaleServiceClient.class);
        ClientServiceClient clientServiceClient = context.getBean(ClientServiceClient.class);

        bookServiceClient.getAll().forEach(System.out::println);
        clientServiceClient.getAll().forEach(System.out::println);
    }
}
