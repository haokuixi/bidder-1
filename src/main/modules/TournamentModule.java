package main.modules;

import main.dto.TournamentDto;
import main.entities.Pair;
import main.entities.Tournament;

import java.util.List;
import java.util.Map;

public interface TournamentModule {
    List<Tournament> getTournamentList(int page);

    void saveTournament(Tournament tournament);

    TournamentDto getById(int id);

    Map<TournamentDto, Pair> getByJudge(int id);

    Map<TournamentDto, Pair> getByPlayer(int id);

    Long countTours();

    List<TournamentDto> transformList(List<Tournament> tournaments);
}
