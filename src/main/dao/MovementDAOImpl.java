package main.dao;

import main.entities.Movement;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository("movementRepository")
public class MovementDAOImpl implements MovementDAO {

    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    @Qualifier(value = "transactionManager")
    private EntityManager em;

    private static final String GET_BY_ID = "getMovementById";
    private static final String GET_BY_PAIRS_NUMBER = "getByPairsNumber";

    @Override
    public void create(Movement m) {
        em.persist(m);
    }

    @Override
    public List<Movement> getByPairsNumber(int pairsNumber) {
        Query query = em.createNamedQuery(GET_BY_PAIRS_NUMBER);
        query.setParameter(1, pairsNumber);

        return query.getResultList();
    }

    @Override
    public Movement getById(int id) {
        Query query = em.createNamedQuery(GET_BY_ID);
        query.setParameter(1, id);

        return (Movement) query.getSingleResult();
    }
}
