package network.communication;

public class ThreadUDPSender extends Thread {
    
    public ThreadUDPSender(RunnableUDPSender sender) {
        super(sender);
    }
}
