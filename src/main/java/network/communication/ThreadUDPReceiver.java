package network.communication;

public class ThreadUDPReceiver extends Thread {

    private RunnableUDPReceiver receiver;

    public ThreadUDPReceiver(RunnableUDPReceiver receiver) {
        super(receiver);
        this.receiver = receiver;
    }

    @Override
    public void interrupt() {
        super.interrupt();
        receiver.stop();
    }
}
