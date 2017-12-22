package test.ui;

import modele.User;
import modele.UserTable;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;


public class TestUserChooser {

    public static void main(String[] args) throws UnknownHostException {
        UserTable users = new UserTable();
        users.put(InetAddress.getByName("127.0.0.1"), new User("127.0.0.1", "Tehema"));
        users.put(InetAddress.getByName("127.0.0.2"), new User("127.0.0.2", "Ainol"));
        users.put(InetAddress.getByName("127.0.0.3"), new User("127.0.0.3", "Nhat"));

        System.out.println("Choisir un utilisateur :");
        displayUsers(users);

        Scanner sc = new Scanner(System.in);
        int choix = sc.nextInt();

        User choisi = getUserChoose(choix, users);

        System.out.println("Communication démarré avec l'utilisateur " + choisi);
    }

    private static User getUserChoose(int choix, UserTable users) throws UnknownHostException {
        InetAddress addr = InetAddress.getByName("127.0.0."+choix);
        return users.getUser(addr);
    }

    private static void displayUsers(UserTable users) {
        int n = 0;
        for (User u : users.getUsers()) {
            System.out.println((n+1) + " - " + u.pseudonyme);
            n++;
        }
    }


}
