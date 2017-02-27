package main.modules;

import main.dto.TournamentDto;
import main.entities.Tournament;

import java.util.List;

public interface TournamentModule {
    List<Tournament> getTournamentList();

    void saveTournament(Tournament tournament);

    TournamentDto getById(int id);

    List<Tournament> getByJudge(int id);

    List<Tournament> getByPlayer(int id);
}
