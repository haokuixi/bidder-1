package main.dao;

import main.entities.AwaitingPlayer;
import main.entities.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Repository("userRepository")
public class UserDAOImpl implements UserDAO {

    private static final String GET_ALL_QUERY = "getAllUsers";
    private static final String GET_BY_LOGIN = "getByLogin";
    private static final String GET_AWAITING_PLAYERS_BY_TOUR = "getByTournament";
    private static final String GET_AWAITING_PLAYERS_BY_USER_AND_TOUR = "getByUserAndTournament";
    private static Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    @PersistenceContext
    @Qualifier(value = "transactionManager")
    private EntityManager em;

    public void create(User e) {
        em.persist(e);
        LOGGER.info("User saved successfully");
    }

    @Override
    public User getById(int id) {
        User u = em.find(User.class, id);
        LOGGER.info("User read successfully");
        return u;
    }

    @Override
    public User update(User u) {
        LOGGER.info("User updating");
        return em.merge(u);
    }

    @Override
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

    @Override
    public User getUserByLogin(String login) {
        Query query = em.createNamedQuery(GET_BY_LOGIN);
        query.setParameter(1, login);
        return (User) query.getSingleResult();
    }

    @Override
    public boolean isValidUser(String login, String password) {
        Query query = em.createNamedQuery(GET_BY_LOGIN);
        query.setParameter(1, login);
        try {
            return new BCryptPasswordEncoder().matches(password, ((User) query.getSingleResult()).getPassword());
        } catch (NoResultException nre) {
            return false;
        }
    }

    @Override
    public Long countUsers() {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(User.class)));
        return em.createQuery(cq).getSingleResult();
    }

    @Override
    public List<User> getAwaitingByTournament(int tourId) {
        Query query = em.createNamedQuery(GET_AWAITING_PLAYERS_BY_TOUR);
        query.setParameter(1, tourId);
        List<AwaitingPlayer> resultList = query.getResultList();
        return new ArrayList<>(resultList.stream().map(a -> a.getPlayer()).collect(Collectors.toList()));
    }

    @Override
    public AwaitingPlayer getByUserAndTournament(int userId, int tourId) {
        Query query = em.createNamedQuery(GET_AWAITING_PLAYERS_BY_USER_AND_TOUR);
        query.setParameter(1, userId);
        query.setParameter(2, tourId);
        return (AwaitingPlayer) query.getSingleResult();
    }

    @Override
    public void quitFromTournament(int userId, int tourId) {
        AwaitingPlayer player = getByUserAndTournament(userId, tourId);
        AwaitingPlayer awaitingPlayer = em.find(AwaitingPlayer.class, player.getPlayerId());

        if (awaitingPlayer != null) {
            em.remove(player);
        }
    }
}
