package main.modules;

import main.entities.Pair;

import java.util.List;

public interface PairModule {

    List<Pair> getByPlayer(String login);

    Pair getByPlayerAndTour(String login, String tourHashedId);

    void removeByPlayerAndTour(String login, String tourHashedId);

    void removeById(int id);

    void createPair(int playerOne, int playerTwo, String tourId);

    Pair getById(int id);
}
