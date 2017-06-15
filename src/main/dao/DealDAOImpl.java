package main.dao;

import main.entities.Deal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@Repository("dealRepository")
public class DealDAOImpl implements DealDAO {

    private static final String GET_BY_ID = "getById";
    private static final String GET_BY_TOUR_ID = "getDealByTourId";

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    @Qualifier(value = "transactionManager")
    private EntityManager em;

    @Transactional
    public void create(Deal d) {
        em.persist(d);
    }

    @Transactional
    public void update(Deal d) {
        em.merge(d);
    }

    @Transactional
    public Deal getDealById(int dealId) {
        Query query = em.createNamedQuery(GET_BY_ID);
        query.setParameter(1, dealId);

        return (Deal) query.getSingleResult();
    }

    @Transactional
    public List<Deal> getByTourId(int tourId) {
        Query query = em.createNamedQuery(GET_BY_TOUR_ID);
        query.setParameter(1, tourId);
        return query.getResultList();
    }
}
