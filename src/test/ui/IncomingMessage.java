package test.ui;

import network.service.IncomingMessageListener;

import java.net.Socket;

public class IncomingMessage implements IncomingMessageListener{

    @Override
    public void onNewMessage(Socket socket, String message) {
        System.out.println("Reçu de " + socket.getInetAddress().getHostAddress() + " : " + message);
    }
}
