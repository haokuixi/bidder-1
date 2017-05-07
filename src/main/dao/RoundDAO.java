package main.dao;

import main.entities.Round;

import java.util.List;

public interface RoundDAO {

    void create(Round r);

    void update(Round r);

    Round getById(int id);

    List<Round> getByTourId(int id);
}
