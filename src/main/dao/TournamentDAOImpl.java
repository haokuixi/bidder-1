package main.dao;

import main.entities.Tournament;
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

@Repository("tournamentRepository")
public class TournamentDAOImpl implements TournamentDAO {
    private static final String GET_ALL_QUERY = "getAllTournaments";
    private static Logger LOGGER = Logger.getLogger(TournamentDAOImpl.class);

    @PersistenceContext(type= PersistenceContextType.EXTENDED)
    @Qualifier(value = "entityManager")
    private EntityManager em;

    @Transactional
    public void create(Tournament t) {
        em.persist(t);
        LOGGER.info("Tournament saved successfully");
    }

    @Transactional
    public Tournament getById(int id) {
        Tournament t = em.find(Tournament.class, id);
        LOGGER.info("Tournament read successfully");
        return t;
    }

    @Transactional
    public Tournament update(Tournament t) {
        LOGGER.info("Tournament updating");
        return em.merge(t);
    }

    @Transactional
    public void remove(int id) {
        Tournament t = em.find(Tournament.class, id);

        if (null != t) {
            em.remove(t);
        }
        LOGGER.info("Tournament deleted successfully");
    }

    public List<Tournament> listAll() {
        Query query = em.createNamedQuery(GET_ALL_QUERY);
        return query.getResultList();
    }
}
