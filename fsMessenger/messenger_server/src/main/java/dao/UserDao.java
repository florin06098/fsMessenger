package dao;

import fSMessenger_common.module.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.Optional;

public class UserDao {
    private EntityManager em;

    public UserDao(EntityManager em) {
        this.em = em;
    }

    public Optional<User> findUserByUsername(String username){
        String query = "SELECT u FROM mess_users u WHERE u.username = :username";
        TypedQuery<User> q = em.createQuery(query, User.class);

        q.setParameter("username", username);
        try {
            Optional<User> singleResult = Optional.of(q.getSingleResult());
            return singleResult;
        } catch (Exception ex){
            ex.printStackTrace();
            return Optional.empty();
        }
    }

    public void addUser(User user){
        em.persist(user);
    }
}
