package main.services;

import main.dto.UserDto;
import main.entities.User;
import main.modules.UserModule;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserModule userModule;

    @Transactional
    public void registerUser(UserDto u) {
        this.userModule.saveUser(u);
    }

    @Transactional
    public List<User> listUsers() {
        return this.userModule.getUserList();
    }

    public UserModule getUserModule() {
        return userModule;
    }

    public void setUserModule(UserModule userModule) {
        this.userModule = userModule;
    }
}
