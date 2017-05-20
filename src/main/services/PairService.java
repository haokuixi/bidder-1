package main.services;

import main.entities.Pair;

import java.util.List;

public interface PairService {

    List<Pair> getByPlayer(String login);

    Pair getByPlayerAndTour(String login, String tourHashedId);

    void createPair(int playerOne, int playerTwo, String tourId);
}
