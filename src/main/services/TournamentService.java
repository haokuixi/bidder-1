package main.services;

import main.dto.TournamentDto;
import main.entities.Tournament;

import java.util.List;

public interface TournamentService {

    void addTournament(Tournament t);

    List<Tournament> listTournament();

    TournamentDto getById(int id);

    List<Tournament> getByJudge(int id);

    List<Tournament> getByPlayer(int id);
}
