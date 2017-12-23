package gui.presenter;

import modele.UserTable;
import network.service.IncomingDatagramPacketListener;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class IncomingDatagramPacket implements IncomingDatagramPacketListener {

    private final ChooseUserPresenter presenter;

    public IncomingDatagramPacket(ChooseUserPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onNewDatagramPacket(DatagramPacket packet) {
        InetAddress address = packet.getAddress();
        String data = new String(packet.getData()).trim();
        System.out.println("Paquet UDP reçu de ["+ address +"] : "+ data); // TODO debug à changer

        // mise à jour de la table
        presenter.addUser(address, data);
    }
}
