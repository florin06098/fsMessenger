package client;

import java.util.ArrayList;
import java.util.List;

public class DisplayRunnable implements Runnable {
    private List<ClientMessageListener> listeners;

    public DisplayRunnable() {
        listeners = new ArrayList<>();
    }

    @Override
    public void run() {
        while (true) {
            String message = ConnectionController.getInstance().receiveMessage();
            notifyAllListeners(message);
        }
    }

    public void addClientMessageListener(ClientMessageListener c) {
        listeners.add(c);
    }

    private void notifyAllListeners(String message) {
        listeners.forEach(c -> {
            c.messageReceived(message);
        });
    }
}