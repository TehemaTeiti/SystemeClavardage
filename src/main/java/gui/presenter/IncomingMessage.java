package gui.presenter;

import network.service.IncomingMessageListener;

import java.net.Socket;

public class IncomingMessage implements IncomingMessageListener {

    @Override
    public void onNewMessage(Socket socket, String message) {
        String addr = String.valueOf(socket.getInetAddress());
        System.out.println("Message reçu de " + addr + " : " + message);
        // TODO affichage du message dans la fenêtre de chat
    }

}
