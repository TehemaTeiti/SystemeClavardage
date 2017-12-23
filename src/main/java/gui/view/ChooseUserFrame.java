package gui.view;

import gui.presenter.ChooseUserView;

import javax.swing.*;
import java.awt.*;

public class ChooseUserFrame extends JFrame implements ChooseUserView {

    public static final String TITLE = "Systeme Clavardage";
    private DefaultListModel<String> listModelUser;
    private final JList<String> listUsers;

    public ChooseUserFrame(ChooseUserListener presenter) {
        super(TITLE);
        setSize(800,300);

        // création des composants
        JLabel labelChooseUser = new JLabel("Liste des utilisateurs connectés");

        listUsers = buildList();

        JButton bOk = new JButton("Chat");
        bOk.addActionListener(e -> presenter.startChat(listUsers.getSelectedIndex()));

        // ajout des composants
        JPanel contentPane = new JPanel(new GridLayout(0,1));
        setContentPane(contentPane);
        add(labelChooseUser);
        add(listUsers);
        add(bOk);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        display();
    }

    private JList<String> buildList() {
        listModelUser = new DefaultListModel<>();
        JList<String> listUsers = new JList<>(listModelUser);
        listUsers.setLayoutOrientation(JList.VERTICAL);
        listUsers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return listUsers;
    }

    public void display() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void addUser(String pseudo) {
        listModelUser.addElement(pseudo);
        if (listUsers.isSelectionEmpty())
            listUsers.setSelectedIndex(0);
    }
}
