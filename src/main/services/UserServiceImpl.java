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
    public void updateUser(User loggedUser, UserDto u) {
        this.userModule.updateUser(loggedUser, u);
    }

    @Transactional
    public List<User> listUsers() {
        return this.userModule.getUserList();
    }

    @Transactional
    public User getUserByLogin(String login) {
        return this.userModule.getUserByLogin(login);
    }

    @Transactional
    public User getUserById(int id) {
        return this.userModule.getById(id);
    }

    public UserModule getUserModule() {
        return userModule;
    }

    public void setUserModule(UserModule userModule) {
        this.userModule = userModule;
    }

    @Override
    public boolean isValidUser(String login, String password) {
        return this.userModule.isValidUser(login, password);
    }

    @Override
    public User transformUser(UserDto userDto, boolean isValid) {
        return userModule.transformUser(userDto, isValid);
    }

    @Override
    public UserDto transformUser(User user) {
        return userModule.transformUser(user);
    }

}
