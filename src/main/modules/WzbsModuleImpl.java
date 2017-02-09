package main.modules;

import main.dao.WzbsDAO;
import main.entities.Wzbs;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WzbsModuleImpl implements WzbsModule {

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

    public WzbsDAO getWzbsDAO() {
        return wzbsDAO;
    }

    public void setWzbsDAO(WzbsDAO wzbsDAO) {
        this.wzbsDAO = wzbsDAO;
    }
}
