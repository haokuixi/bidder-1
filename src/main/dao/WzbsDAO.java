package main.dao;

import main.entities.Wzbs;

import java.util.List;

public interface WzbsDAO{

    Wzbs getById(int id);
    List<Wzbs> listAll();
    Wzbs getByShortName(String shortName);
}
