package main.services;

import main.entities.Movement;
import main.modules.MovementModule;

import java.util.List;

public class MovementServiceImpl implements MovementService {

    MovementModule movementModule;

    @Override
    public void create(Movement m) {

    }

    @Override
    public List<Movement> getByPairsNumber(int pairsNumber) {
        return null;
    }

    @Override
    public Movement getByid(String hashedId) {
        return null;
    }

    public MovementModule getMovementModule() {
        return movementModule;
    }

    public void setMovementModule(MovementModule movementModule) {
        this.movementModule = movementModule;
    }
}
