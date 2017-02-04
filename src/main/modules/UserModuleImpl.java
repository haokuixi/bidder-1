package main.modules;

import main.dao.UserDAO;
import main.entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserModuleImpl implements UserModule {

    @Autowired
    UserDAO userDAO;

    @Override
    public List<User> getUserList() {
        return userDAO.listAll();
    }

    @Override
    public void saveUser(User user) {
        userDAO.create(user);
    }
}
