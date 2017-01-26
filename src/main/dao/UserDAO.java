package main.dao;

import main.entities.User;

import java.util.List;

public interface UserDAO {

    void createUser(User u);
    User getUserById(int id);
    void updateUser(User u);
    void removeUser(int id);
    List<User> listUsers();


}
