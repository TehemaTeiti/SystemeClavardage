package modele;

import java.net.InetAddress;
import java.util.LinkedList;
import java.util.List;

public class UserList {

    private List<User> users;

    public UserList() {
        this.users = new LinkedList<>();
    }

    public void add(User user) {
        users.add(user);
    }

    public void remove(int index) {
        users.remove(index);
    }

    public User get(int index) {
        return users.get(index);
    }

    public boolean exist(InetAddress addr, String pseudo) {
        User user = new User(addr, pseudo);
        return users.contains(user);
    }

    public void printAll() {
        users.forEach(System.out::println);
    }

}
