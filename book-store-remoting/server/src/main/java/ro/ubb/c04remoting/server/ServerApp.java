package ro.ubb.c04remoting.server;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by nicu.
 */
public class ServerApp {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext("ro.ubb.c04remoting.server.config");
        System.out.println("hello");
    }
}
