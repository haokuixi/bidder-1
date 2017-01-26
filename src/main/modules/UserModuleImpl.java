package main.modules;

import main.dao.UserDAO;
import main.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserModuleImpl implements UserModuleApi {

    @Autowired
    UserDAO userDAO;

    public List<User> getUserList() {
        return userDAO.listUsers();
    }
}
