package main.services;

import main.dto.RoundResultDto;

import java.util.List;

public interface RoundResultService {

    void create(RoundResultDto r);

    void update(RoundResultDto r);

    RoundResultDto getRoundResultById(int resultId);

    List<RoundResultDto> getByTourAndRoundNumber(String tourHashedId, int roundNo);

}
