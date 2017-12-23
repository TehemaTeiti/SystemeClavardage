package network.communication;

import network.service.IncomingDatagramPacketListener;
import network.service.UDPReceiverMessage;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

public class RunnableUDPReceiver implements Runnable {

    private int port;
    private IncomingDatagramPacketListener incoming;

    private boolean stop;
    private UDPReceiverMessage receiver;

    public RunnableUDPReceiver(int port, IncomingDatagramPacketListener incoming) {
        this.port = port;
        this.incoming = incoming;
        this.stop = false;
    }

    public void stop() {
        stop = true;
        receiver.close();
    }

    @Override
    public void run() {
        while(!stop) {
            try {
                receiver = new UDPReceiverMessage(port);
                receiver.receive(incoming);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Arrêt du thread UDPReceiver");
    }
}
