package main.dao;

import main.entities.Pair;
import main.entities.Tournament;
import main.entities.User;

import java.util.List;
import java.util.Map;

public interface TournamentDAO {

    void create(Tournament t);

    Tournament getById(int id);

    Tournament update(Tournament t);

    void remove(int id);

    List<Tournament> listAll();

    List<Tournament> getToursByJudge(User user);

    Map<Tournament, Pair> getToursByPlayer(User user);

    Long countTours();
}
