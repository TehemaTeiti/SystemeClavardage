package network.service;

import java.net.Socket;

public interface IncomingMessageListener {
    public void onNewMessage(Socket socket, String message);
}
