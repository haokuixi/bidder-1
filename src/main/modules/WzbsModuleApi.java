package main.modules;

import main.entities.Wzbs;

import java.util.List;

public interface WzbsModuleApi {
    List<Wzbs> getWzbsList();
    Wzbs getWzbsById(int id);
    Wzbs getWzbsByShortName(String shortName);
}
