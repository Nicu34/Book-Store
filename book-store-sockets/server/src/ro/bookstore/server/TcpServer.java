package ro.bookstore.server;


import ro.bookstore.common.BookStoreException;
import ro.bookstore.common.BookStoreRuntimeException;
import ro.bookstore.common.domain.Message;
import ro.bookstore.common.utils.MenuEnum;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.function.UnaryOperator;

/**
 * @author radu.
 */
public class TcpServer {
    private ExecutorService executorService;
    private int serverPort;

    private Map<MenuEnum, UnaryOperator<Message>> methodHandlers = new HashMap<>();

    public TcpServer(ExecutorService executorService, int serverPort) {
        this.executorService = executorService;
        this.serverPort = serverPort;
    }

    public void addHandler(MenuEnum action, UnaryOperator<Message> methodHandler) {
        methodHandlers.put(action, methodHandler);
    }

    public void startServer() throws BookStoreException {
        System.out.println("Starting server");
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            System.out.println("Server socket created");
            while (true) {
                System.out.println("Waiting for clients");
                Socket socket = serverSocket.accept();
                System.out.println("Client accepted");
                executorService.submit(new ClientHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new BookStoreException(e);
        }
    }

    private class ClientHandler implements Runnable {
        private Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
                 ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream())) {
                Message request = ((Message) inputStream.readObject());
                System.out.println("received request: " + request);

                UnaryOperator<Message> methodHandler = methodHandlers.get(request.getMenuEnum());
                Message response = methodHandler.apply(request);
                System.out.println("computed response: " + response);
                outputStream.writeObject(response);
                outputStream.flush();
                System.out.println("response written to outputStream");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                throw new BookStoreRuntimeException(e);
            } finally {
                try {
                    if (socket != null) {
                        socket.close();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        }
    }
}
