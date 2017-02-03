package main.dao;

import main.entities.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository("userRepository")
public class UserDAOImpl implements UserDAO{

    private static final String GET_ALL_QUERY = "getAllUsers";
    private static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    @PersistenceContext(type= PersistenceContextType.EXTENDED)
    @Qualifier(value = "entityManager")
    private EntityManager em;

    @Transactional
    public void create(User e) {
        em.persist(e);
        LOGGER.info("User saved successfully");
    }

    @Transactional
    public User getById(int id) {
        User u = em.find(User.class, id);
        LOGGER.info("User read successfully");
        return u;
    }

    @Transactional
    public User update(User u) {
        LOGGER.info("User updating");
        return em.merge(u);
    }

    @Transactional
    public void remove(int id) {
        User u = em.find(User.class, id);

        if (null != u) {
            em.remove(u);
        }
        LOGGER.info("User deleted successfully");
    }

    public List<User> listAll() {
        Query query = em.createNamedQuery(GET_ALL_QUERY);
        return query.getResultList();
    }
}
