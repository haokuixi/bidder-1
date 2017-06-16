package main.dao;

import main.entities.RoundResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@Repository("roundResultRepository")
public class RoundResultDAOImpl implements RoundResultDAO {

    private static final String GET_BY_TOUR_AND_ROUND_NUMBER = "getResultByTourAndRound";

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    @Qualifier(value = "transactionManager")
    private EntityManager em;

    @Override
    public void create(RoundResult r) {
        em.persist(r);
    }

    @Override
    public void update(RoundResult r) {
        em.merge(r);
    }

    @Override
    public RoundResult getRoundResultById(int resultId) {
        return null;
    }

    @Override
    public List<RoundResult> getByTourAndRoundNumber(int tourId, int roundNo) {
        Query query = em.createNamedQuery(GET_BY_TOUR_AND_ROUND_NUMBER);
        query.setParameter("tour", tourId);
        query.setParameter("roundNumber", roundNo);

        return query.getResultList();
    }
}
