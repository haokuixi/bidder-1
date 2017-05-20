package main.services;

import main.entities.Pair;
import main.modules.PairModule;

import java.util.List;

public class PairServiceImpl implements PairService{

    PairModule pairModule;

    @Override
    public List<Pair> getByPlayer(String login) {
        return pairModule.getByPlayer(login);
    }

    @Override
    public Pair getByPlayerAndTour(String login, String tourHashedId) {
        return pairModule.getByPlayerAndTour(login, tourHashedId);
    }

    @Override
    public void createPair(int playerOne, int playerTwo, String tourId) {
        pairModule.createPair(playerOne, playerTwo, tourId);
    }

    public PairModule getPairModule() {
        return pairModule;
    }

    public void setPairModule(PairModule pairModule) {
        this.pairModule = pairModule;
    }
}
