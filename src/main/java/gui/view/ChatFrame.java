package gui.view;

import gui.presenter.ChatView;

import javax.swing.*;
import java.awt.*;

public class ChatFrame extends JFrame implements ChatView {

    public ChatFrame(ChatListener presenter, String title) {
        super(title);
        setSize(800,300);

        // création des composants

        // ajout des composants
        JPanel contentPane = new JPanel(new GridLayout(0,1));
        setContentPane(contentPane);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        display();
    }

    public void display() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
