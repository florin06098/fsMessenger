package server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ClientsContainer {
    private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private static final List<ServerThread> clienti = new ArrayList<>();

    public static void addClient(ServerThread client){
        try {
            lock.writeLock().lock();
            clienti.add(client);
        } finally {
            lock.writeLock().unlock();
        }
    }
    public static void sendMessageToAll(String message){
        try {
            System.out.println("Trying to send the message to everyone");
            lock.readLock().lock();
            clienti.forEach(client -> {
                try {
                    client.sendMessage(message);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } finally {
            lock.readLock().unlock();
        }
    }
}