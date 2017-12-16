package vue.main;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;

public class MainPanel extends JPanel {

    private DefaultListModel<String> dlm;

    public MainPanel() {
        super(new FlowLayout(FlowLayout.CENTER));

        // initialisation des composants
        JLabel labelTitle = new JLabel("Système de clavardage");

        dlm = new DefaultListModel<>();
        JList<String> listUsers = new JList<>(dlm);
        listUsers.setLayoutOrientation(JList.VERTICAL);

        // ajout des composants dans le JPanel
        this.add(labelTitle);
        this.add(listUsers);

        addUser("Tehema");
        addUser("Ainol");
    }

    public void loadListUsers(Collection<String> users) {
        dlm.clear();
        for (String user : users) {
            dlm.addElement(user);
        }
    }

    public boolean removeUser(String user) {
        return dlm.removeElement(user);
    }

    public void addUser(String user) {
        dlm.addElement(user);
    }

}
