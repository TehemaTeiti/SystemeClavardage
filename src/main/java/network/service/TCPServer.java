package network.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer  {

    private ServerSocket serverSocket;

    public TCPServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public Socket accept() throws IOException {
        return serverSocket.accept();
    }
}
