package client;

import fSMessenger_common.module.AuthRequest;
import fSMessenger_common.module.AuthType;
import fSMessenger_common.module.User;

import java.io.*;
import java.net.Socket;

public class ConnectionController {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    private ConnectionController() {
        try {
            this.socket = new Socket("localhost", 4444);
            this.out = new ObjectOutputStream(socket.getOutputStream());
            this.in = new ObjectInputStream(socket.getInputStream());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static final class SingletonHolder {
        private static final ConnectionController INSTANCE = new ConnectionController();
    }

    public static ConnectionController getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean register(String username, String password, String last_name, String first_name, String age, String email) {
        try {
            AuthRequest.Builder authRequestBuilder = new AuthRequest.Builder(username, password, AuthType.REQUEST);
            if(!last_name.isEmpty()){
                authRequestBuilder.withLastName(last_name);
            }
            if(!first_name.isEmpty()){
                authRequestBuilder.withFirstName(first_name);
            }
            if(!age.isEmpty()){
                int integerAge = Integer.parseInt(age);
                authRequestBuilder.withAge(integerAge);
            }
            if(!email.isEmpty()){
                authRequestBuilder.withEmail(email);
            }

            AuthRequest authRequest = authRequestBuilder.build();
            out.writeObject(authRequest);

            return (Boolean) in.readObject();
        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public boolean login(String username, String password){
        try{
            AuthRequest authRequest = new AuthRequest.Builder(username, password, AuthType.LOGIN).build();
            out.writeObject(authRequest);

            return (Boolean) in.readObject();
        } catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    public void sendMessage(String mesaj) {
        try {
            out.writeObject(mesaj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String receiveMessage(){
        try {
            return in.readObject().toString();
        } catch (IOException | ClassNotFoundException ex){
            ex.printStackTrace();
            return "";
        }
    }
}
