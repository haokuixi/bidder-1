package main.dao;

import main.entities.User;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    private static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void createUser(User u) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(u);
        LOGGER.info("User saved successfully");
    }

    public User getUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User u = (User) session.load(User.class, id);
        LOGGER.info("User got successfully");
        return u;
    }

    public void updateUser(User u) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(u);
        LOGGER.info("User updated successfully");
    }

    public void removeUser(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User u = (User) session.load(User.class, id);

        if(null != u) {
            session.delete(u);
        }
        LOGGER.info("User deleted successfully");
    }

    public List<User> listUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List userList = session.createQuery("from User").list();
        return userList;
    }
}
