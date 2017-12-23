package gui.presenter;

import gui.view.ChatFrame;
import gui.view.ChooseUserListener;
import modele.User;
import modele.UserList;
import network.App_Param_Network;
import network.communication.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ChooseUserPresenter implements ChooseUserListener {

    private ChooseUserView view;
    private UserList users;
    private String currentPseudo;
    private ThreadUDPSender thUDPSender;
    private ThreadUDPReceiver thUdpReceiver;
    private ThreadTCPServer threadTCPServer;
    private IncomingMessage incoming;


    public ChooseUserPresenter(String currentPseudo) {
            this.currentPseudo = currentPseudo;
            users = new UserList();
    }

    public void setView(ChooseUserView view) {
        this.view = view;
        System.out.println("Presenter ChooseUser : ajout de la vue OK"); //TODO debug
        go();
    }

    private void go() {
        try {
            // Ecoute du serveur UDP
            System.out.println("Connexion en tant que " + currentPseudo); // TODO debug
            startTCPServer(App_Param_Network.PORT_TCP_SERVER);
            startUDPServer(App_Param_Network.PORT_UDP_SERVER);
            startUDPSender(currentPseudo);
        } catch (IOException e) {
            closeThreads();
        }
    }

    public void closeThreads() {
        threadTCPServer.interrupt();
        thUdpReceiver.interrupt();
    }

    private void startTCPServer(int port) throws IOException {
        incoming = new IncomingMessage();
        RunnableTCPServer tcpServer = new RunnableTCPServer(port, incoming);
        threadTCPServer = new ThreadTCPServer(tcpServer);
        threadTCPServer.start();
        System.out.println("Ecoute TCP Serveur sur le port " + port);
    }

    private void startUDPSender(String message) throws UnknownHostException {
        InetAddress ipBroadcast = InetAddress.getByName(App_Param_Network.IP_BORADCAST);
        int portUdpServer = App_Param_Network.PORT_UDP_SERVER;
        int periodUdpBroadcast = App_Param_Network.PERIOD_UDP_BROADCAST;

        RunnableUDPSender udpSender = new RunnableUDPSender(ipBroadcast, portUdpServer, message, periodUdpBroadcast);
        thUDPSender = new ThreadUDPSender(udpSender);
        thUDPSender.start();
    }

    private void startUDPServer(int portUdpServer) {
        IncomingDatagramPacket incoming = new IncomingDatagramPacket(this);

        RunnableUDPReceiver udpReceiver = new RunnableUDPReceiver(portUdpServer, incoming);
        thUdpReceiver = new ThreadUDPReceiver(udpReceiver);
        thUdpReceiver.start();
        System.out.println("Ecoute UDP sur le port " + portUdpServer); // TODO debug
    }


    public void addUser(InetAddress addr, String pseudo) {
        System.out.println("Presenter ChooseUser : ajout d'un user"); //TODO debug
        if (!users.exist(addr, pseudo)) {
           view.addUser(pseudo);
           users.add(new User(addr, pseudo));
        }
        else {
            // TODO gestion cas du changement de pseudonyme
        }
    }

    @Override
    public void startChat(int selectedIndex) {
        User user = users.get(selectedIndex);
        System.out.println("Démarrage du chat avec " + user.pseudonyme + "\taddr : " + user.addr);

        // TODO lancer fenêtre de chat avec utilisateur sélectionné
        ChatPresenter chatPresenter = new ChatPresenter(currentPseudo, user, incoming);
        ChatFrame chatFrame = new ChatFrame(chatPresenter, user.pseudonyme);
        chatPresenter.setView(chatFrame);
    }
}
