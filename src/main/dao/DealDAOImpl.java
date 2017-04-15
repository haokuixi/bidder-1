package main.dao;

import main.entities.Deal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

@Repository("dealRepository")
public class DealDAOImpl implements DealDAO {

    private static final String GET_BY_ID = "getById";

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    @Qualifier(value = "transactionManager")
    private EntityManager em;

    @Transactional
    public void create(Deal d) {
        em.persist(d);
    }

    @Transactional
    public Deal getDealById(int dealId) {
        Query query = em.createNamedQuery(GET_BY_ID);
        query.setParameter(1, dealId);

        return (Deal) query.getSingleResult();
    }
}