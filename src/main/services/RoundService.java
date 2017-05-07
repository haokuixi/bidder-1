package main.services;

import main.entities.Round;

import java.util.List;

public interface RoundService {
    void create(Round r);

    void create(List<Round> rounds);

    void beginRound(Round r);

    Round getById(int id);

    Round getById(String id);

    List<Round> getByTourId(int id);

    List<Round> getByTourId(String id);
}
