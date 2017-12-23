package gui.view;

import gui.presenter.ChatView;

import javax.swing.*;
import java.awt.*;

public class ChatFrame extends JFrame implements ChatView {

    private final JTextArea littleTextArea;
    private final JTextArea bigTextArea;

    public ChatFrame(ChatListener presenter, String title) {
        super(title);
        setSize(800,300);

        // création des composants
        bigTextArea = new JTextArea(20, 100);
        bigTextArea.setEditable(false);

        littleTextArea = new JTextArea(2, 80);
        littleTextArea.setEditable(true);

        JButton bSendMsg = new JButton("Envoyer");
        bSendMsg.addActionListener(e -> presenter.sendMessage(littleTextArea.getText()));

        // ajout des composants
        JPanel contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        JPanel centerPane = new JPanel(new FlowLayout());
        centerPane.add(bigTextArea);

        JPanel southPane = new JPanel(new FlowLayout());
        southPane.add(littleTextArea);
        southPane.add(bSendMsg);

        add(centerPane, BorderLayout.CENTER);
        add(southPane, BorderLayout.SOUTH);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        display();
    }

    @Override
    public void clearText() {
        littleTextArea.setText("");
    }

    @Override
    public void addMessage(String text) {
        bigTextArea.append(text+"\n");
    }

    public void display() {
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}
