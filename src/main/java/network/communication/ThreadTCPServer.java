package network.communication;

import java.io.IOException;

public class ThreadTCPServer extends Thread {

    private RunnableTCPServer server;

    public ThreadTCPServer(RunnableTCPServer server) {
        super(server);
        this.server = server;
    }

    @Override
    public void interrupt() {
        super.interrupt();
        try {
            server.stop();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
