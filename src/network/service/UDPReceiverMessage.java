package network.service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPReceiverMessage {

    public static final int BUFFER_SIZE = 30;

    public static void receive(int port, IncomingDatagramPacketListener incoming) throws IOException {
        DatagramSocket socket = new DatagramSocket(port);
        DatagramPacket packet = new DatagramPacket(new byte[BUFFER_SIZE], BUFFER_SIZE);
        socket.receive(packet);
        incoming.onNewDatagramPacket(packet);
        socket.close();
    }

}
