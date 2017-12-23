package modele;

import java.net.InetAddress;
import java.util.Collection;
import java.util.HashMap;

public class UserTable {

    private HashMap<InetAddress, User> userTable;

    public UserTable() {
        this.userTable = new HashMap<>();
    }

    public void put(InetAddress addr, User u) {
        userTable.put(addr, u);
    }

    public Collection<User> getUsers() {
        return userTable.values();
    }

    public User getUser(InetAddress addr) {
        return userTable.get(addr);
    }

    @Override
    public String toString() {
        String tab = "\t\t\t";
        String ch = "ADRESSE" + tab + "| PSEUDONYME\n";
        for (InetAddress addr : userTable.keySet()) {
            ch += addr + tab + "| " + userTable.get(addr).pseudonyme + "\n";
        }
        return ch;
    }
}
