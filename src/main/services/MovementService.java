package main.services;

import main.entities.Movement;

import java.util.List;

public interface MovementService {

    void create(Movement m);

    List<Movement> getByPairsNumber(int pairsNumber);

    Movement getByid(String hashedId);

}
