package main.services;

import main.entities.Wzbs;

import java.util.List;

public interface WzbsService {

    List<Wzbs> getAll();
    Wzbs getByShortName(String shortName);
    Wzbs getById(int id);
}
