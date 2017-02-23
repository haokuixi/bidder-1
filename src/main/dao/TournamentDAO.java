package main.dao;

import main.entities.Tournament;

import java.util.List;

public interface TournamentDAO {

    void create(Tournament t);
    Tournament getById(int id);
    Tournament update(Tournament t);
    void remove(int id);
    List<Tournament> listAll();
}
