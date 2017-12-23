package network.communication;

import network.service.IncomingMessageListener;
import network.service.TCPReceiverMessage;

import java.io.IOException;
import java.net.Socket;

public class RunnableTCPReceiver implements Runnable {

    private Socket socket;
    private IncomingMessageListener incoming;

    public RunnableTCPReceiver(Socket socket, IncomingMessageListener incoming) {
        this.socket = socket;
        this.incoming = incoming;
    }

    @Override
    public void run() {
        try {
            TCPReceiverMessage.receive(socket, incoming);
        } catch (IOException e) {
            try {
                socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
}
