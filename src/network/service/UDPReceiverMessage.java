package network.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiverMessage {

    public static final int BUFFER_SIZE = 30;
    private int port;
    private DatagramSocket socket;

    public UDPReceiverMessage(int port) {
        this.port = port;
    }

    public void close() {
        socket.close();
    }

    public void receive(IncomingDatagramPacketListener incoming) throws IOException {
        socket = new DatagramSocket(port);
        DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
        socket.receive(packet);
        incoming.onNewDatagramPacket(packet);
        socket.close();
    }

}
