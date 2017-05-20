package main.dao;

import main.entities.Pair;
import main.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository("pairRepository")
public class PairDAOImpl implements PairDAO {

    private static final String GET_ALL_QUERY = "getAllPairs";
    private static final String GET_BY_PLAYER = "getByPlayer";
    private static final String GET_ALL_BY_TOUR_QUERY = "getByTourId";
    private static final String GET_BY_PLAYER_AND_TOUR = "getByPlayerAndTour";

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    @Qualifier(value = "transactionManager")
    private EntityManager em;

    @Transactional
    public void create(Pair p) {
        em.persist(p);
    }

    @Transactional
    public Pair getById(int id) {
        return em.find(Pair.class, id);
    }

    @Transactional
    public Pair update(Pair p) {
        return em.merge(p);
    }

    @Transactional
    public void remove(int id) {
        Pair p = em.find(Pair.class, id);

        if (null != p) {
            em.remove(p);
        }
    }

    @Transactional
    public void remove(Pair pair) {
        em.remove(pair);
    }

    @Transactional
    public List<Pair> listAll() {
        Query query = em.createNamedQuery(GET_ALL_QUERY);
        return query.getResultList();
    }

    @Transactional
    public List<Pair> listByTourId(int id) {
        Query query = em.createNamedQuery(GET_ALL_BY_TOUR_QUERY);
        query.setParameter(1, id);
        return query.getResultList();
    }

    @Transactional
    public List<Pair> getByPlayer(User player) {
        Query query = em.createNamedQuery(GET_BY_PLAYER);
        query.setParameter("player", player);
        return query.getResultList();
    }

    @Transactional
    public Pair getByPlayerAndTour(String login, int tourId) {
        Query query = em.createNamedQuery(GET_BY_PLAYER_AND_TOUR);
        query.setParameter("player", login);
        query.setParameter("tour", tourId);

        return (Pair) query.getSingleResult();
    }
}
