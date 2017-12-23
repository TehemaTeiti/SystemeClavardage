package gui.presenter;

import gui.view.ChatFrame;
import gui.view.ChatListener;
import modele.User;

public class ChatPresenter implements ChatListener {

    private ChatView view;
    private String currentPseudo;
    private User remoteUser;

    public ChatPresenter(String currentPseudo, User remoteUser) {
        this.currentPseudo = currentPseudo;
        this.remoteUser = remoteUser;
    }

    public void setView(ChatView view) {
        this.view = view;
        go();
    }

    private void go() {
        System.out.println("Démarrage discussion\n\tCurrent : " +currentPseudo+"\n\tRemote : "+remoteUser); // TODO debug
    }

}
