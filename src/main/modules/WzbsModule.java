package main.modules;

import main.entities.Wzbs;

import java.util.List;

public interface WzbsModule {
    List<Wzbs> getWzbsList();
    Wzbs getWzbsById(int id);
    Wzbs getWzbsByShortName(String shortName);
}
