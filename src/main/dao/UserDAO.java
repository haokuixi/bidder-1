package main.dao;

import main.entities.AwaitingPlayer;
import main.entities.User;

import java.util.List;

public interface UserDAO {

    void create(User u);

    User getById(int id);

    User update(User u);

    void remove(int id);

    List<User> listAll();

    boolean isValidUser(String login, String password);

    User getUserByLogin(String login);

    Long countUsers();

    List<User> getAwaitingByTournament(int tourId);

    AwaitingPlayer getByUserAndTournament(int userId, int tourId);

    void quitFromTournament(int userId, int tourId);

    void enterIntoTournament(AwaitingPlayer awaitingPlayer);
}
