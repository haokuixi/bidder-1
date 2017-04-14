package main.dao;

import main.entities.Deal;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Repository("dealRepository")
public class DealDAOImpl implements DealDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    @Qualifier(value = "transactionManager")
    private EntityManager em;

    @Transactional
    public void create(Deal d) {
        em.persist(d);
    }
}
