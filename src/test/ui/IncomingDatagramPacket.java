package test.ui;

import modele.User;
import modele.UserTable;
import network.service.IncomingDatagramPacketListener;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class IncomingDatagramPacket implements IncomingDatagramPacketListener {

    private UserTable userTable;

    public IncomingDatagramPacket(UserTable userTable) {
        this.userTable = userTable;
    }

    @Override
    public void onNewDatagramPacket(DatagramPacket packet) {
        InetAddress addr = packet.getAddress();
        String pseudonyme = new String(packet.getData());
        System.err.println("Message reçu de " + addr + " : " + pseudonyme);

        userTable.put(addr, new User(addr, pseudonyme));
        System.out.println(userTable.toString());
    }
}
