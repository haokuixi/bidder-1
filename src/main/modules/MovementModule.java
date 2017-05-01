package main.modules;

import main.entities.Movement;

import java.util.List;

public interface MovementModule {

    void create(Movement m);

    List<Movement> getByPairsNumber(int pairsNumber);

    Movement getById(int id);

    Movement getByid(String hashedId);
}
