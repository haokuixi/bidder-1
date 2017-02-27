package main.services;

import main.dto.TournamentDto;
import main.entities.Pair;
import main.entities.Tournament;

import java.util.List;
import java.util.Map;

public interface TournamentService {

    void addTournament(Tournament t);

    List<Tournament> listTournament();

    TournamentDto getById(int id);

    Map<Tournament, Pair> getByJudge(int id);

    Map<Tournament, Pair> getByPlayer(int id);
}
