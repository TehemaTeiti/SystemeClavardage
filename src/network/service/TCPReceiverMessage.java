package network.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class TCPReceiverMessage {

    public static void receive(Socket socket, IncomingMessageListener incoming) throws IOException {
        InputStreamReader stream = new InputStreamReader(socket.getInputStream());
        BufferedReader reader = new BufferedReader(stream);
        String message = reader.readLine();
        incoming.onNewMessage(socket, message);
        reader.close();
        stream.close();
        socket.close();
    }

}
