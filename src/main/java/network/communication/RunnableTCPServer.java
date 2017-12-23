package network.communication;

import network.service.IncomingMessageListener;
import network.service.TCPServer;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

public class RunnableTCPServer implements Runnable {

    private TCPServer server;
    private boolean stop;
    private IncomingMessageListener incoming;

    public RunnableTCPServer(int port, IncomingMessageListener incoming) throws IOException {
        server = new TCPServer(port);
        stop = false;
        this.incoming = incoming;
    }

    public void stop() throws IOException {
        stop = true;
    }

    @Override
    public void run() {
        Socket socket = null;
        ThreadTCPReceiver thReceiver;
        RunnableTCPReceiver receiver;
        while(!stop) {
            try {
                socket = server.accept();
                receiver = new RunnableTCPReceiver(socket, incoming);
                thReceiver = new ThreadTCPReceiver(receiver);
                thReceiver.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // fermeture de la socket
        if (socket != null)
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}
