package gui.presenter;

import gui.view.ChatFrame;
import gui.view.ChatListener;
import modele.User;
import network.App_Param_Network;
import network.communication.RunnableTCPSender;
import network.communication.ThreadTCPSender;
import network.communication.ThreadTCPServer;

import java.sql.Timestamp;

public class ChatPresenter implements ChatListener {

    private ChatView view;
    private String currentPseudo;
    private User remoteUser;
    private IncomingMessage incoming;

    public ChatPresenter(String currentPseudo, User remoteUser, IncomingMessage incoming) {
        this.currentPseudo = currentPseudo;
        this.remoteUser = remoteUser;
        this.incoming = incoming;
        incoming.addPresenter(remoteUser.addr, this);
    }

    public void setView(ChatView view) {
        this.view = view;
        go();
    }

    private void go() {
        System.out.println("Démarrage discussion\n\tCurrent : " +currentPseudo+"\n\tRemote : "+remoteUser); // TODO debug
    }

    @Override
    public void receiveMessage(String message) {
        // TODO afficher message
        System.out.println("Reçu : " + message); // TODO debug
        view.addMessage("Receive" + formatMessage(message, remoteUser.pseudonyme));
    }

    @Override
    public void sendMessage(String message) {
        System.out.println("Envoi : " + message); // TODO debug
        // TODO afficher message
        view.addMessage("Send" + formatMessage(message, currentPseudo));
        view.clearText();
        // TODO envoyer message
        startTCPSender(message);
    }

    private void startTCPSender(String message) {
        RunnableTCPSender tcpSender = new RunnableTCPSender(remoteUser.addr, App_Param_Network.PORT_TCP_SERVER, message);
        ThreadTCPSender threadTCPSender = new ThreadTCPSender(tcpSender);
        threadTCPSender.start();
    }

    private String formatMessage(String message, String pseudo) {
        return "[" + new Timestamp(System.currentTimeMillis()) + "] " + pseudo + " : "  + message;
    }
}
