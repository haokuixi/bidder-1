package main.services;

import main.dto.MovementDto;

import java.util.List;

public interface MovementService {

    void create(MovementDto m);

    List<MovementDto> getByPairsNumber(int pairsNumber);

    MovementDto getByid(String hashedId);

}
