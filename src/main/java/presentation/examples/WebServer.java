package presentation.examples;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

class WebServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket socket = new ServerSocket(80)) {
            while (true) {
                final Socket connection = socket.accept();
                Runnable task = () -> handleRequest(connection);
                new Thread(task).start();
            }
        }
    }
    private static void handleRequest(Socket connection) {
        // your spring code
    }
}