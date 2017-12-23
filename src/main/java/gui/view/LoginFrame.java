package gui.view;

import gui.presenter.LoginPresenter;
import gui.presenter.LoginView;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame implements LoginView {

    public static final String TITLE = "Systeme Clavardage";

    public LoginFrame(LoginListener presenter) {
        super(TITLE);
        setSize(400,300);

        // création des composants
        JLabel labelPseudo = new JLabel("Pseudonyme");

        JTextField tfPseudo = new JTextField();

        JButton bPseudo = new JButton("Ok");
        bPseudo.addActionListener(e -> presenter.onClickButton(tfPseudo.getText()));

        // ajout des composants
        JPanel contentPane = new JPanel(new GridLayout(0,1));
        setContentPane(contentPane);
        add(labelPseudo);
        add(tfPseudo);
        add(bPseudo);

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        display();
    }

    public void display() {
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        LoginPresenter presenter = new LoginPresenter();
        LoginFrame f = new LoginFrame(presenter);
        presenter.setView(f);
    }
}
