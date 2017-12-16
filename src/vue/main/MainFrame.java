package vue.main;

import javax.swing.*;

public class MainFrame extends JFrame {

    public static final String TITLE = "Système de clavardage";

    public MainFrame() {
        super(TITLE);
        setSize(1200,600);
        setContentPane(new MainPanel());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    public static void main(String[] args) {
        MainFrame f = new MainFrame();
    }

}
