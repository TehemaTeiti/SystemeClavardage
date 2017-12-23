package ui;

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
        String data = (new String(packet.getData())).trim();

        System.err.println("Message reçu de " + addr + " : " + data);

        userTable.put(addr, new User(addr,data));
        System.out.println(userTable.toString());
    }
}
