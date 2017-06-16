package main.dao;


import main.entities.RoundResult;

import java.util.List;

public interface RoundResultDAO {

    void create(RoundResult r);

    void update(RoundResult r);

    RoundResult getRoundResultById(int resultId);

    List<RoundResult> getByTourAndRoundNumber(int tourId, int roundNo);

}
