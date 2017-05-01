package main.dao;


import main.entities.Movement;

import java.util.List;

public interface MovementDAO {

    void create(Movement m);

    List<Movement> getByPairsNumber(int pairsNumber);

    Movement getById(int id);
}
