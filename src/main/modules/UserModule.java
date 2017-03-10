package main.modules;

import main.dto.UserDto;
import main.entities.User;

import java.util.List;

public interface UserModule {
    List<User> getUserList(int page);

    void saveUser(UserDto user);

    void updateUser(User loggedUser, UserDto user);

    User transformUser(UserDto userDto, boolean isValid);

    boolean isValidUser(String login, String password);

    User getUserByLogin(String login);

    User getById(int id);

    UserDto transformUser(User user);

    Long countUsers();
}
