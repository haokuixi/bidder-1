package main.dao;

import main.entities.Tournament;
import main.entities.User;

import java.util.List;

public interface TournamentDAO {

    void create(Tournament t);

    Tournament getById(int id);

    Tournament update(Tournament t);

    void remove(int id);

    List<Tournament> listAll();

    List<Tournament> getToursByJudge(User user);

    List<Tournament> getToursByPlayer(User user);
}
