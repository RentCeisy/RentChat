package RentChat.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientThread extends Thread {

    private final Socket socket;
    private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;


    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        objectInputStream = new ObjectInputStream(socket.getInputStream());
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
    }


}
