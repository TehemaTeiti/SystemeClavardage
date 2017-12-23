package network.communication;

import network.service.UDPSenderMessage;

import java.io.IOException;
import java.net.InetAddress;

import static java.lang.Thread.sleep;

public class RunnableUDPSender implements Runnable {

    private InetAddress addr;
    private int port;
    private String message;
    private int period;
    private boolean stop;

    public RunnableUDPSender(InetAddress addr, int port, String message, int period) {
        this.addr = addr;
        this.port = port;
        this.message = message;
        this.period = period;
        this.stop = false;
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while(!stop) {
            try {
                UDPSenderMessage.send(addr, port, message);
            } catch (IOException e) {
                this.stop();
                e.printStackTrace();
            }
            try {
                sleep(period);
            } catch (InterruptedException e) {
                this.stop();
                e.printStackTrace();
            }
        }
        System.out.println("Arrêt du thread UDPSender");
    }
}
