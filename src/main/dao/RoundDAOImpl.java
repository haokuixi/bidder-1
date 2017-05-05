package main.dao;

import main.entities.Round;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@Repository("roundRepository")
public class RoundDAOImpl implements RoundDAO {

    private static final String GET_BY_ID = "getRoundById";
    private static final String GET_BY_TOUR_ID = "getRoundByTourId";

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    @Qualifier(value = "transactionManager")
    private EntityManager em;

    @Override
    public void create(Round r) {
        em.persist(r);
    }

    @Override
    public Round getById(int id) {
        Query query = em.createNamedQuery(GET_BY_ID);
        query.setParameter(1, id);
        return (Round) query.getSingleResult();
    }

    @Override
    public List<Round> getByTourId(int id) {
        Query query = em.createNamedQuery(GET_BY_TOUR_ID);
        query.setParameter(1, id);
        return query.getResultList();
    }
}
