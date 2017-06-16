package main.services;

import main.dto.RoundResultDto;
import main.modules.RoundResultModule;

import java.util.List;

public class RoundResultServiceImpl implements RoundResultService {

    RoundResultModule roundResultModule;

    @Override
    public void create(RoundResultDto r) {

    }

    @Override
    public void update(RoundResultDto r) {

    }

    @Override
    public RoundResultDto getRoundResultById(int resultId) {
        return null;
    }

    @Override
    public List<RoundResultDto> getByTourAndRoundNumber(String tourHashedId, int roundNo) {
        return roundResultModule.getRoundResultsByTourAndRoundNumber(tourHashedId, roundNo);
    }

    public RoundResultModule getRoundResultModule() {
        return roundResultModule;
    }

    public void setRoundResultModule(RoundResultModule roundResultModule) {
        this.roundResultModule = roundResultModule;
    }
}
