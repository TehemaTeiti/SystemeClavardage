package ui;

import modele.UserTable;
import network.communication.RunnableUDPReceiver;
import network.communication.RunnableUDPSender;
import network.communication.ThreadUDPReceiver;
import network.communication.ThreadUDPSender;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TestBroadcastUI {

    public static final String IP_BROADCAST = "255.255.255.255";
    public static final int PORT = 5001;
    public static final int PERIOD_BROADCAST = 2000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrer votre pseudo :");
        String pseudo = sc.nextLine();

        try {
            // Envoi UDP Broadcast
            InetAddress addr = InetAddress.getByName(IP_BROADCAST);
            RunnableUDPSender sender = new RunnableUDPSender(addr, PORT, pseudo, PERIOD_BROADCAST);
            ThreadUDPSender thSender = new ThreadUDPSender(sender);
            thSender.start();
            System.out.println("Démarrage de l'envoi broadcast");

            // Ecoute UDP
            UserTable userTable = new UserTable();
            IncomingDatagramPacket incoming = new IncomingDatagramPacket(userTable);
            RunnableUDPReceiver receiver = new RunnableUDPReceiver(PORT, incoming);
            ThreadUDPReceiver thReceiver = new ThreadUDPReceiver(receiver);
            thReceiver.start();
            System.out.println("Démarrage de l'écoute UDP sur le port " + PORT);

            // Arret du programme
            System.out.println("Appuyez sur entrée pour arrêter le programme");
            sc.nextLine();
            thSender.interrupt();
            thReceiver.interrupt();
            System.out.println("Arrêt du programme");

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }



    }

}
