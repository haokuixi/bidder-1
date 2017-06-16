package main.modules;

import main.dto.RoundResultDto;
import main.entities.RoundResult;

import java.util.List;

public interface RoundResultModule {

    RoundResult transformRoundResult(RoundResultDto dto);

    RoundResultDto transformRoundResult(RoundResult roundResult);

    List<RoundResultDto> getRoundResultsByTourAndRoundNumber(String tourHashedId, int roundNumber);
}
