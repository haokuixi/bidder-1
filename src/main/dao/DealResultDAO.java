package main.dao;

import main.entities.DealResult;

import java.util.List;

public interface DealResultDAO {

    void create(DealResult d);

    List<DealResult> getByDealId(int dealId);
}
