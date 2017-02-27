package main.modules;

import main.dao.PairDAO;
import main.dao.UserDAO;
import main.entities.Pair;

import java.util.List;

public class PairModuleImpl implements PairModule {

    PairDAO pairDAO;
    UserDAO userDAO;

    @Override
    public List<Pair> getByPlayer(int id) {
        return pairDAO.getByPlayer(userDAO.getById(id));
    }

    public PairDAO getPairDAO() {
        return pairDAO;
    }

    public void setPairDAO(PairDAO pairDAO) {
        this.pairDAO = pairDAO;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
