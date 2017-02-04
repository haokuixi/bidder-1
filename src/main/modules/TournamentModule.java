package main.modules;

import main.entities.Tournament;

import java.util.List;

public interface TournamentModule {
    List<Tournament> getTournamentList();
    void saveTournament(Tournament tournament);
    Tournament getById(int id);
}
