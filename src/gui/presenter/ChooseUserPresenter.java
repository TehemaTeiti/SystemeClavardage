package gui.presenter;

import gui.view.ChooseUserListener;
import modele.User;
import modele.UserTable;
import network.communication.RunnableUDPReceiver;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ChooseUserPresenter implements ChooseUserListener {

    private ChooseUserView view;
    private UserTable users;
    private User currentUser;

    public ChooseUserPresenter(String currentPseudo) {
        try {
            currentUser = new User(InetAddress.getLocalHost(), currentPseudo);
            users = new UserTable();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void setView(ChooseUserView view) {
        this.view = view;
    }

    @Override
    public void startChat(int selectedIndex) {
        // TODO lancer fenêtre de chat avec utilisateur sélectionné
    }
}
