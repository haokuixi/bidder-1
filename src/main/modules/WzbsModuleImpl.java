package main.modules;

import main.dao.WzbsDAO;
import main.entities.Wzbs;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WzbsModuleImpl implements WzbsModuleApi {

    @Autowired
    WzbsDAO wzbsDAO;

    @Override
    public List<Wzbs> getWzbsList() {
        return wzbsDAO.listAll();
    }

    @Override
    public Wzbs getWzbsById(int id) {
        return wzbsDAO.getById(id);
    }

    @Override
    public Wzbs getWzbsByShortName(String shortName) {
        return wzbsDAO.getByShortName(shortName);
    }
}
