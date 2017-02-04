package main.services;

import main.entities.User;
import main.modules.UserModule;
import main.modules.WzbsModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserModule userModule;
    @Autowired
    private WzbsModule wzbsModule;

    @Transactional
    public void addUser(User u) {
        u.setWzbs(wzbsModule.getWzbsByShortName(u.getWzbs().getShortName()));
        this.userModule.saveUser(u);
    }

    @Transactional
    public List<User> listUsers() {
        return this.userModule.getUserList();
    }

}
