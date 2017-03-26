package main.services;


import main.dto.UserDto;
import main.entities.User;

import java.util.List;

public interface UserService {

    void registerUser(UserDto u);

    void updateUser(User loggedUser, UserDto u);

    List<User> listUsers(int page);

    boolean isValidUser(String login, String password);

    User getUserByLogin(String login);

    User getUserById(int id);

    User transformUser(UserDto userDto, boolean isValid);

    UserDto transformUser(User user);

    Long countUsers();

    List<User> getAwaitingByTournament(String tourId);

    void quitFromTournament(int userId, String tourId);
}
