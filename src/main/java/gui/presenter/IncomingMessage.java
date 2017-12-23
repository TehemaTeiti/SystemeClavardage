package gui.presenter;

import gui.view.ChatListener;
import network.service.IncomingMessageListener;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;

public class IncomingMessage implements IncomingMessageListener {

    private HashMap<InetAddress, ChatListener> tableAddrPresenter;

    public IncomingMessage() {
        tableAddrPresenter = new HashMap<>();
    }

    public void addPresenter(InetAddress addr, ChatListener presenter) {
        tableAddrPresenter.put(addr, presenter);
        System.out.println("addPresenter("+addr+","+presenter+")"); // TODO debug
    }

    @Override
    public void onNewMessage(Socket socket, String message) {
        InetAddress addr = socket.getInetAddress();
        // TODO affichage du message dans la fenêtre de chat
        System.out.println("incoming addr receive : "+addr+")"); // TODO debug

        if (tableAddrPresenter.containsKey(addr)) {
            System.out.println("incoming presenter trouvé"); // TODO debug
            ChatListener presenter = tableAddrPresenter.get(addr);
            presenter.receiveMessage(message);
        }
    }

}
