package main.modules;

import main.dto.MovementDto;
import main.entities.Movement;

import java.util.List;

public interface MovementModule {

    void create(MovementDto m);

    List<MovementDto> getByPairsNumber(int pairsNumber);

    MovementDto getById(int id);

    MovementDto getByid(String hashedId);

    MovementDto transformMovement(Movement m);

    Movement transformMovement(MovementDto m);

}
