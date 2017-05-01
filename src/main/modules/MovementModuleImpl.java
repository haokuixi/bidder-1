package main.modules;

import main.dao.MovementDAO;
import main.entities.Movement;
import main.utils.DataHash;

import java.util.List;

public class MovementModuleImpl implements MovementModule {

    MovementDAO movementDAO;

    @Override
    public void create(Movement m) {
        movementDAO.create(m);
    }

    @Override
    public List<Movement> getByPairsNumber(int pairsNumber) {
        return movementDAO.getByPairsNumber(pairsNumber);
    }

    @Override
    public Movement getById(int id) {
        return movementDAO.getById(id);
    }

    @Override
    public Movement getByid(String hashedId) {
        return movementDAO.getById(new DataHash().decode(hashedId));
    }

    public MovementDAO getMovementDAO() {
        return movementDAO;
    }

    public void setMovementDAO(MovementDAO movementDAO) {
        this.movementDAO = movementDAO;
    }
}
