package main.modules;

import main.entities.Round;

import java.util.List;

public interface RoundModule {
    void create(Round r);

    void create(List<Round> rounds);

    Round getById(int id);

    Round getById(String id);

    List<Round> getByTourId(int id);

    List<Round> getByTourId(String id);
}
