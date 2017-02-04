package main.dao;

import main.entities.Tournament;

import java.util.List;

public interface TournamentDAO {

    void create(Tournament u);
    Tournament getById(int id);
    Tournament update(Tournament u);
    void remove(int id);
    List<Tournament> listAll();
}
