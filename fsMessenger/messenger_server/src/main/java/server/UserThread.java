//package server;
//
//import fSMessenger_common.module.User;
//import service.RegisterService;
//
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
//import java.net.Socket;
//
//public class UserThread extends Thread{
//    private Socket socket;
//    private ObjectInputStream objReader;
//    private ObjectOutputStream objWritter;
//
//    public UserThread(Socket socket) throws IOException {
//        this.socket = socket;
//        this.objReader = new ObjectInputStream(socket.getInputStream());
//        this.objWritter = new ObjectOutputStream(socket.getOutputStream());
//    }
//
//    @Override
//    public void run() {
//        while(true){
//            try {
//                User user = (User) objReader.readObject();
//                User register = RegisterService.getInstance().register(user); // Always logged in, at first
//                objWritter.writeObject("You are now logged in as: " + register.getUsername());
//            } catch(Exception ex){
//                ex.printStackTrace();
//            }
//        }
//    }
//}
