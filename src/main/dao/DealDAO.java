package main.dao;

import main.entities.Deal;

public interface DealDAO {

    void create(Deal d);

    Deal getDealById(int dealId);
}
