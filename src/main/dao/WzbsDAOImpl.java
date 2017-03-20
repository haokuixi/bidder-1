package main.dao;

import main.entities.Wzbs;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository("wzbsRepository")
public class WzbsDAOImpl implements WzbsDAO {

    private static final String GET_ALL_QUERY = "getAllWzbs";
    private static final String GET_BY_SHORT_NAME = "getByShortName";
    private static Logger LOGGER = Logger.getLogger(WzbsDAOImpl.class);

    @PersistenceContext
    @Qualifier(value = "entityManager")
    private EntityManager em;

    @Override
    public Wzbs getById(int id) {
        Wzbs w = em.find(Wzbs.class, id);
        LOGGER.info("User read successfully");
        return w;
    }

    @Override
    public List<Wzbs> listAll() {
        Query query = em.createNamedQuery(GET_ALL_QUERY);
        List resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Wzbs getByShortName(String shortName) {
        Query query = em.createNamedQuery(GET_BY_SHORT_NAME);
        query.setParameter("short", shortName);
        return (Wzbs) query.getSingleResult();
    }
}
