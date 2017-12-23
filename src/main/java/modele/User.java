package modele;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class User {

    public InetAddress addr;
    public String pseudonyme;

    public User(InetAddress addr, String pseudonyme) {
        this.addr = addr;
        this.pseudonyme = pseudonyme;
    }

    public User(String addr, String pseudonyme) throws UnknownHostException {
        this(InetAddress.getByName(addr), pseudonyme);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof User) {
            User u = (User) obj;
            return (u.pseudonyme.equals(this.pseudonyme) && u.addr.equals(this.addr));
        }
        else {
            return false;
        }
    }

    @Override
    public String toString() {
        return addr.getHostAddress() + " " + pseudonyme;
    }

}
