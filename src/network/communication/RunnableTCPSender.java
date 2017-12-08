package network.communication;

import network.service.TCPSenderMessage;

import java.io.IOException;
import java.net.InetAddress;

public class RunnableTCPSender implements Runnable {

    private InetAddress addr;
    private int port;
    private String message;

    public RunnableTCPSender(InetAddress addr, int port, String message) {
        this.addr = addr;
        this.port = port;
        this.message = message;
    }

    @Override
    public void run() {
        try {
            TCPSenderMessage.send(addr, port, message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
