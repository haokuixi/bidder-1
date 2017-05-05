package main.modules;

import main.dao.RoundDAO;
import main.entities.Round;
import main.utils.DataHash;

import java.util.List;

public class RoundModuleImpl implements RoundModule {

    private RoundDAO roundDAO;

    @Override
    public void create(Round r) {
        roundDAO.create(r);
    }

    @Override
    public void create(List<Round> rounds) {
        for(Round r:rounds) {
            roundDAO.create(r);
        }
    }

    @Override
    public Round getById(int id) {
        return roundDAO.getById(id);
    }

    @Override
    public Round getById(String id) {
        return roundDAO.getById(new DataHash().decode(id));
    }

    @Override
    public List<Round> getByTourId(int id) {
        return roundDAO.getByTourId(id);
    }

    @Override
    public List<Round> getByTourId(String id) {
        return roundDAO.getByTourId(new DataHash().decode(id));
    }

    public RoundDAO getRoundDAO() {
        return roundDAO;
    }

    public void setRoundDAO(RoundDAO roundDAO) {
        this.roundDAO = roundDAO;
    }
}
