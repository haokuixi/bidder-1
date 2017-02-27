package main.services;

import main.entities.Pair;
import main.modules.PairModule;

import java.util.List;

public class PairServiceImpl implements PairService{

    PairModule pairModule;

    @Override
    public List<Pair> getByPlayer(int id) {
        return pairModule.getByPlayer(id);
    }

    public PairModule getPairModule() {
        return pairModule;
    }

    public void setPairModule(PairModule pairModule) {
        this.pairModule = pairModule;
    }
}
