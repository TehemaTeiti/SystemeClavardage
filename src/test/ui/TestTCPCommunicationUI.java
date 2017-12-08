package test.ui;

import modele.UserTable;
import network.communication.*;
import network.service.IncomingMessageListener;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TestTCPCommunicationUI {

    public static final String IP_BROADCAST = "255.255.255.255";
    public static final int PORT_BROADCAST = 5001;
    public static final int PORT_TCPSERVER = 5002;
    public static final int PERIOD_BROADCAST = 2000;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Entrer pseudonyme");
        String pseudonyme = sc.nextLine();

        goUDP(pseudonyme);

        try {
            ThreadTCPServer thServer = startServerTCP(PORT_TCPSERVER, new IncomingMessage());
            System.out.println("Entrer @ destinataire");
            String addr = sc.nextLine();
            askCommunication(InetAddress.getByName(addr), PORT_TCPSERVER);
            thServer.interrupt();
            System.out.println("Arrêt du programme");

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

    public static ThreadTCPServer startServerTCP(int port, IncomingMessageListener incoming) throws IOException {
        RunnableTCPServer server = new RunnableTCPServer(port, incoming);
        ThreadTCPServer thServer = new ThreadTCPServer(server);
        thServer.start();
        System.out.println("Ecoute du serveur sur le port " + port);
        return thServer;
    }

    public static void askCommunication(InetAddress addr, int port)  {
        boolean stop = false;
        System.out.println("Démarrage de la connexion avec " + addr);
        while(!stop) {
            System.out.print("> ");
            Scanner sc = new Scanner(System.in);
            String message = sc.nextLine();
            if (message.equals("exit")) {
                stop = true;
            }
            RunnableTCPSender sender = new RunnableTCPSender(addr, port, message);
            ThreadTCPSender thSender = new ThreadTCPSender(sender);
            thSender.start();
        }
    }

    public static void goUDP(String pseudo) {

        try {
            InetAddress addr = InetAddress.getByName(IP_BROADCAST);
            RunnableUDPSender sender = new RunnableUDPSender(addr, PORT_BROADCAST, pseudo, PERIOD_BROADCAST);
            ThreadUDPSender thSender = new ThreadUDPSender(sender);
            thSender.start();
            System.out.println("Démarrage de l'envoi broadcast");

            UserTable userTable = new UserTable();
            IncomingDatagramPacket incoming = new IncomingDatagramPacket(userTable);
            RunnableUDPReceiver receiver = new RunnableUDPReceiver(PORT_BROADCAST, incoming);
            ThreadUDPReceiver thReceiver = new ThreadUDPReceiver(receiver);
            thReceiver.start();
            System.out.println("Démarrage de l'écoute UDP sur le port " + PORT_BROADCAST);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


}
