package main.dao;

import main.entities.Deal;

import java.util.List;

public interface DealDAO {

    void create(Deal d);

    Deal getDealById(int dealId);

    List<Deal> getByTourId(int tourId);
}
