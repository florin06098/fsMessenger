package server;

import java.io.IOException;
public class MainServer {
    public static void main(String[] args) throws IOException {

        while(true){
            ServerThread client = ServerEngine.getInstance().connectClient();
            ClientsContainer.addClient(client);
        }
    }
}