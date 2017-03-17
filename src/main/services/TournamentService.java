package main.services;

import main.dto.TournamentDto;
import main.entities.Pair;

import java.util.List;
import java.util.Map;

public interface TournamentService {

    void addTournament(TournamentDto t);

    List<TournamentDto> listTournament(int page);

    TournamentDto getById(int id);

    Map<TournamentDto, Pair> getByJudge(int id);

    Map<TournamentDto, Pair> getByPlayer(int id);

    Long countTours();
}
