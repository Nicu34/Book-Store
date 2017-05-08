package ro.bookstore.client.tcp;

import ro.bookstore.common.BookStoreException;
import ro.bookstore.common.domain.Message;
import ro.bookstore.common.service.ServiceException;
import ro.bookstore.common.utils.MenuEnum;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by nicu on 5.02.2017.
 */
public class TcpClient {
    private String serviceHost;
    private int servicePort;

    public TcpClient(String serviceHost, int servicePort) {
        this.serviceHost = serviceHost;
        this.servicePort = servicePort;
    }

    public Message sendAndReceive(Message request) {
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;
        System.out.println("Connecting to service");
        try (Socket socket = new Socket(serviceHost, servicePort)) {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(request);
            outputStream.flush();
            System.out.println("Request sent: " + request.toString());
            inputStream = new ObjectInputStream(socket.getInputStream());
            Message response = ((Message) inputStream.readObject());
            if (response.getMenuEnum().equals(MenuEnum.OK)) {
                System.out.println("Response OK: " + response.toString());
                return response;
            } else {
                System.out.println("Response ERROR: " + response.getDataMessage());
                throw new ServiceException(response.getDataMessage());
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new ServiceException(e);
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
