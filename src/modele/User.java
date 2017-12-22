package modele;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class User {

    private InetAddress addr;
    public String pseudonyme;

    public User(InetAddress addr, String pseudonyme) {
        this.addr = addr;
        this.pseudonyme = pseudonyme;
    }

    public User(String addr, String pseudonyme) throws UnknownHostException {
        this(InetAddress.getByName(addr), pseudonyme);
    }

    @Override
    public String toString() {
        return "IP : " + addr + ", pseudo : " + pseudonyme;
    }

}
