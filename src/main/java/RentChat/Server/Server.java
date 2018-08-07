package RentChat.Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int Port = 1989;
    private static ServerSocket serverSocket;
    private Socket socket;

    public Server() {
        try {
            serverSocket = new ServerSocket(Port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.go();
    }

    public void go() {
        while(true) {
            try{
                socket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(socket);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
