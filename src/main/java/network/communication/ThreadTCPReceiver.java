package network.communication;

public class ThreadTCPReceiver extends Thread {

    public ThreadTCPReceiver(RunnableTCPReceiver receiver) {
        super(receiver);
    }
}
