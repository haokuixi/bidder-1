package main.modules;

import main.entities.Pair;

import java.util.List;

public interface PairModule {

    List<Pair> getByPlayer(int id);

    Pair getByPlayerAndTour(int playerId, int tourId);

    void removeByPlayerAndTour(int playerId, int tourId);

    void removeById(int id);
}
