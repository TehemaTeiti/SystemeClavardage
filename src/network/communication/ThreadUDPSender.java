package network.communication;

public class ThreadUDPSender extends Thread {

    private RunnableUDPSender sender;

    public ThreadUDPSender(RunnableUDPSender sender) {
        super(sender);
    }
}
