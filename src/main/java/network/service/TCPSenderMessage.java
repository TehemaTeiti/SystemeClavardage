package network.service;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCPSenderMessage {

    public static void send(InetAddress addr, int port, String message) throws IOException {
        Socket socket = new Socket(addr, port);
        PrintWriter writer = new PrintWriter(socket.getOutputStream());
        writer.println(message);
        writer.close();
        socket.close();
    }

}
