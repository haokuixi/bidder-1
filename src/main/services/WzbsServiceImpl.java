package main.services;

import main.entities.Wzbs;
import main.modules.WzbsModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WzbsServiceImpl implements WzbsService{

    @Autowired
    private WzbsModule wzbsModule;

    @Override
    public List<Wzbs> getAll() {
        return this.wzbsModule.getWzbsList();
    }

    @Override
    public Wzbs getByShortName(String shortName) {
        return this.getByShortName(shortName);
    }

    @Override
    public Wzbs getById(int id) {
        return this.wzbsModule.getWzbsById(id);
    }
}
