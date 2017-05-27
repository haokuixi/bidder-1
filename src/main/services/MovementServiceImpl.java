package main.services;

import main.dto.MovementDto;
import main.dto.XmlContentDto;
import main.modules.MovementModule;

import java.util.List;

public class MovementServiceImpl implements MovementService {

    MovementModule movementModule;

    @Override
    public void create(MovementDto m) {
        movementModule.create(m);
    }

    @Override
    public void create(XmlContentDto xml) {
        movementModule.create(xml);
    }

    @Override
    public List<MovementDto> getByPairsNumber(int pairsNumber) {
        return movementModule.getByPairsNumber(pairsNumber);
    }

    @Override
    public MovementDto getByid(String hashedId) {
        return movementModule.getByid(hashedId);
    }

    @Override
    public MovementDto createDto(XmlContentDto xml) {
        return movementModule.createDto(xml);
    }

    public MovementModule getMovementModule() {
        return movementModule;
    }

    public void setMovementModule(MovementModule movementModule) {
        this.movementModule = movementModule;
    }
}
