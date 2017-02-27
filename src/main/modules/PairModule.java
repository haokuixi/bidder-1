package main.modules;

import main.entities.Pair;

import java.util.List;

public interface PairModule {

    List<Pair> getByPlayer(int id);
}
