package main.dao;

import main.entities.Pair;
import main.entities.User;

import java.util.List;

public interface PairDAO {

    void create(Pair p);

    Pair getById(int id);

    Pair update(Pair p);

    void remove(int id);

    void remove(Pair pair);

    List<Pair> listAll();

    List<Pair> listByTourId(int id);

    List<Pair> getByPlayer(User player);

    Pair getByPlayerAndTour(int playerId, int tourId);
}
