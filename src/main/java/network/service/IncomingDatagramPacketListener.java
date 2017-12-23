package network.service;

import java.net.DatagramPacket;

public interface IncomingDatagramPacketListener {
    public void onNewDatagramPacket(DatagramPacket packet);
}
