package main.services;

import main.entities.Tournament;

import java.util.List;

public interface TournamentService {

    void addTournament(Tournament t);
    List<Tournament> listTournament();
    Tournament getById(int id);
}
