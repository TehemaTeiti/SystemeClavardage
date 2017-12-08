package network.service;

public interface IncomingMessageListener {
    public void onNewMessage(String message);
}
