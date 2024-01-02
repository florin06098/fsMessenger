package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerEngine {
    private ServerSocket ss;

    private ServerEngine() {
        try {
            this.ss = new ServerSocket(4444);
            System.out.println("Server started");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static final class SingletonHolder {
        private static final ServerEngine INSTANCE = new ServerEngine();
    }

    public static ServerEngine getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ServerThread connectClient() throws IOException {
        Socket clientSocket = ss.accept(); // waiting for a client to send a message
        ServerThread st = new ServerThread(clientSocket);
        st.start();

        return st;
    }
}