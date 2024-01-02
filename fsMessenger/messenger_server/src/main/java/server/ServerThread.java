package server;

import fSMessenger_common.module.AuthRequest;
import fSMessenger_common.module.AuthType;
import fSMessenger_common.module.User;
import service.RegisterService;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread{
    private final ObjectInputStream in;
    private final ObjectOutputStream out;
    public ServerThread(Socket socket) throws IOException {
        this.in = new ObjectInputStream(socket.getInputStream());
        this.out = new ObjectOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        boolean isConnected = false;

        while(true){
            System.out.println("Verificam login");
            while(!isConnected){
                System.out.println("Trying to login");
                try {
                    AuthRequest authRequest = (AuthRequest) in.readObject();
                    isConnected = executeOperationBasedOnType(authRequest);
                    System.out.println("logged? " + isConnected);
                } catch (Exception ex){
                    ex.printStackTrace();
                    throw new RuntimeException(ex);
                }
            }
            System.out.println("Succesfully logged in");
            try {
                System.out.println("trying to receive the message");
                String message = receiveMessage();
                System.out.println("Message receieved: " + message);
                ClientsContainer.sendMessageToAll(message);
            } catch(Exception ex){
                ex.printStackTrace();
                throw new RuntimeException(ex);
            }
        }
    }

    private boolean executeOperationBasedOnType(AuthRequest authRequest) throws IOException {
        System.out.println("Checking the operation type: " + authRequest.getAuthTypeOperation());
        if(authRequest.getAuthTypeOperation().equals(AuthType.REQUEST)){
            return executeRequest(authRequest);
        } else if(authRequest.getAuthTypeOperation().equals(AuthType.LOGIN)){
            return executeLogin(authRequest);
        } else{
            return false;
        }
    }

    private boolean executeRequest(AuthRequest authRequest) throws IOException {
        System.out.println("Execute sign up method");
        User user = new User(authRequest.getFirst_name(), authRequest.getLast_name(), authRequest.getEmail(), authRequest.getUsername(), authRequest.getPassword(), authRequest.getAge());
        System.out.println("user: " + user.getUsername());
        boolean userRegistered = RegisterService.getInstance().register(user);
        System.out.println("Is it a new user? " + userRegistered);
        out.writeObject(userRegistered);

        return userRegistered;
    }

    private boolean executeLogin(AuthRequest authRequest) throws IOException {
        System.out.println("Execute login");
        boolean login = RegisterService.getInstance().login(authRequest.getUsername(), authRequest.getPassword());
        System.out.println("Can login? " + login);
        out.writeObject(login);

        return login;
    }

    public void sendMessage(String message) throws IOException {
        out.writeObject(message);
    }

    public String receiveMessage() throws IOException, ClassNotFoundException {
        String mesaj = in.readObject().toString();
        System.out.println("in.readLine(): " + mesaj);
        return mesaj;
    }


/*    public String getClientName(){
        return "ServerThread: " + username;
    }*/
}
