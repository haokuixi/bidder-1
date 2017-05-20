package main.services;

import main.dto.UserDto;
import main.entities.User;
import main.modules.UserModule;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class UserServiceImpl implements UserService {
    private UserModule userModule;

    @Override
    public void registerUser(UserDto u) {
        this.userModule.saveUser(u);
    }

    @Override
    public void updateUser(User loggedUser, UserDto u) {
        this.userModule.updateUser(loggedUser, u);
    }

    @Override
    public List<User> listUsers(int page) {
        return this.userModule.getUserList(page);
    }

    @Override
    public User getUserByLogin(String login) {
        return this.userModule.getUserByLogin(login);
    }

    @Override
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

    @Override
    public Long countUsers() {
        return userModule.countUsers();
    }

    @Override
    public List<User> getAwaitingByTournament(String tourId) {
        return userModule.getAwaitingByTournament(tourId);
    }

    @Override
    public void quitFromTournament(String login, String tourId) {
        userModule.quitFromTournament(login, tourId);
    }

    @Override
    public void enterIntoTournament(String login, String tourId) {
        userModule.enterIntoTournament(login, tourId);
    }

    @Override
    public void quitWithPair(String login, String tourId) {
        userModule.quitWithPair(login, tourId);
    }
}
