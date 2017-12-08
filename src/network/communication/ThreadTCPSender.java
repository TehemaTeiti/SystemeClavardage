package network.communication;

public class ThreadTCPSender extends Thread {

    public ThreadTCPSender(RunnableTCPSender sender) {
        super(sender);
    }

}
