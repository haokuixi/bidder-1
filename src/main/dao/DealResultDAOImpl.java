package main.dao;

import main.entities.DealResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

public class DealResultDAOImpl implements DealResultDAO {

    private static final String GET_BY_DEAL_ID = "getByDealId";

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    @Qualifier(value = "transactionManager")
    private EntityManager em;

    @Transactional
    public void create(DealResult d) {
        em.persist(d);
    }

    @Override
    public List<DealResult> getByDealId(int dealId) {
        Query query = em.createNamedQuery(GET_BY_DEAL_ID);
        query.setParameter(1, dealId);
        return query.getResultList();
    }
}
