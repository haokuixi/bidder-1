package main.services;

import main.entities.Pair;

import java.util.List;

public interface PairService {

    List<Pair> getByPlayer(int id);
}
