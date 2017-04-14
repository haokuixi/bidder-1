package main.dao;

import main.entities.Pair;
import main.entities.Tournament;
import main.entities.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Repository("tournamentRepository")
public class TournamentDAOImpl implements TournamentDAO {
    private static final String GET_ALL_QUERY = "getAllTournaments";
    private static final String GET_WHERE_JUDGE = "getWhereJudge";
    private static final String GET_WHERE_PLAYER = "getWherePlayer";
    private static Logger LOGGER = Logger.getLogger(TournamentDAOImpl.class);

    @PersistenceContext
    @Qualifier(value = "transactionManager")
    private EntityManager em;

    @Override
    public void create(Tournament t) {
        em.persist(t);
        LOGGER.info("Tournament saved successfully");
    }

    @Override
    public Tournament getById(int id) {
        Tournament t = em.find(Tournament.class, id);
        LOGGER.info("Tournament read successfully");
        return t;
    }

    @Override
    public void update(Tournament t) {
        LOGGER.info("Tournament updating");
        em.persist(t);
    }

    @Override
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

    @Override
    public List<Tournament> getToursByJudge(User user) {
        Query query = em.createNamedQuery(GET_WHERE_JUDGE);
        query.setParameter(1, user);
        List resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Map<Tournament, Pair> getToursByPlayer(User user) {
        Query query = em.createNamedQuery(GET_WHERE_PLAYER);
        query.setParameter("player", user);
        List resultList = query.getResultList();
        Map<Tournament, Pair> map = new HashMap<>();
        for (Object result : resultList) {
            Object[] values = (Object[]) result;
            map.put((Tournament) values[0], (Pair) values[1]);
        }
        return map;
    }

    @Override
    public Long countTours() {
        CriteriaBuilder qb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = qb.createQuery(Long.class);
        cq.select(qb.count(cq.from(Tournament.class)));
        return em.createQuery(cq).getSingleResult();
    }
}
