package main.services;

import main.dto.MovementDto;
import main.dto.XmlContentDto;

import java.util.List;

public interface MovementService {

    void create(MovementDto m);

    void create(XmlContentDto xml);

    List<MovementDto> getByPairsNumber(int pairsNumber);

    MovementDto getByid(String hashedId);

    MovementDto createDto(XmlContentDto xml);

}
