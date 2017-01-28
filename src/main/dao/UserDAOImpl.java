package main.dao;

import main.entities.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);
    private static final String GET_ALL_QUERY = "getAll";

    @PersistenceContext
    @Qualifier(value = "entityManager")
    private EntityManager em;


    public void createUser(User u) {
        em.persist(u);
        LOGGER.info("User saved successfully");
    }

    public User getUserById(int id) {
        User u = em.find(User.class, id);
        LOGGER.info("User read successfully");
        return u;
    }

    public User updateUser(User u) {
        LOGGER.info("User updating");
        return em.merge(u);
    }

    public void removeUser(int id) {
        User u = em.find(User.class, id);

        if(null != u) {
            em.remove(u);
        }
        LOGGER.info("User deleted successfully");
    }

    public List<User> listUsers() {
        Query query = em.createNamedQuery(GET_ALL_QUERY);
        return query.getResultList();
    }
}
