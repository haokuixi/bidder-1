package main.modules;

import main.dto.RoundDto;
import main.entities.Round;

import java.util.List;

public interface RoundModule {
    void create(Round r);

    void create(List<Round> rounds);

    void beginRound(Round round);

    void update(Round round);

    Round getById(int id);

    Round getById(String id);

    List<Round> getByTourId(int id);

    List<Round> getByTourId(String id);

    List<RoundDto> getDtosByTourId(int id);

    List<RoundDto> getDtosByTourId(String id);

    Round transformRound(RoundDto roundDto);

    RoundDto transformRound(Round round);

    void completeRound(Round r);
}
