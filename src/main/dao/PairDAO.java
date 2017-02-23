package main.dao;

import main.entities.Pair;

import java.util.List;

public interface PairDAO {

    void create(Pair p);
    Pair getById(int id);
    Pair update(Pair p);
    void remove(int id);
    List<Pair> listAll();
    List<Pair> listByTourId(int id);
}
