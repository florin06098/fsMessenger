package client;

import javax.swing.*;
import java.security.Timestamp;
import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ChatFrame implements ClientMessageListener{
    private JTextArea textArea1;
    private JPanel panel1;
    private JTextArea textArea2;
    private JButton trimiteButton;
    private final String name;
    private final DisplayRunnable displayRunnable;

    public ChatFrame(String name) {
        this.name = name;
        trimiteButton.addActionListener(ev->sendMessage());
        displayRunnable = new DisplayRunnable();
        displayRunnable.addClientMessageListener(this);
    }

    private void sendMessage(){
        String message = textArea2.getText();
        Instant currentTimestamp = Instant.now();
        ConnectionController.getInstance().sendMessage(name + ": " + message + "(" + currentTimestamp + ")");
        textArea2.setText(null);
    }

    public JPanel getPanel() {
        return panel1;
    }

    public void startDisplayThread(){
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(displayRunnable);
        if(executorService.isTerminated()){
            executorService.shutdown();
        }
    }

    @Override
    public void messageReceived(String message) {
        textArea1.append(message + "\n");
    }
}
