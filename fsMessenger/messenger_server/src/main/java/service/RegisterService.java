package service;

import dao.UserDao;
import fSMessenger_common.module.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.Optional;

public class RegisterService {
    private final EntityManagerFactory emf;
    private RegisterService(){
        emf = Persistence.createEntityManagerFactory("fsMessengerPU");
    }

    private static final class SingletonHolder{
        private static final RegisterService INSTANCE = new RegisterService();
    }

    public static RegisterService getInstance(){
        return SingletonHolder.INSTANCE;
    }

    public boolean register(User user){
        EntityManager em = emf.createEntityManager();
        UserDao userDao = new UserDao(em);
        try{
            em.getTransaction().begin();
            Optional<User> existentUser = userDao.findUserByUsername(user.getUsername());
            if(existentUser.isPresent()){
                System.out.println("Exista si este prezent: " + existentUser.get().getUsername());
                return false;
            }
            userDao.addUser(user);
            em.getTransaction().commit();
        } finally{
            em.close();
        }
        return true;
    }

    public boolean login(String username, String password){
        EntityManager em = emf.createEntityManager();
        UserDao userDao = new UserDao(em);
        try{
            em.getTransaction().begin();
            Optional<User> existentUser = userDao.findUserByUsername(username);
            if(existentUser.isPresent()){
                // verify password
                System.out.println("S-a gasit userul: " + existentUser.get());
                boolean passwordMatch = verifyPassword(existentUser.get(), password);
                System.out.println("Parola introdusa este: " + passwordMatch);
                return passwordMatch;
            } else{
                return false;
            }
        } finally{
            em.close();
        }
    }

    private boolean verifyPassword(User userFound, String password){
        return userFound.getPassword().equals(password);
    }


}
