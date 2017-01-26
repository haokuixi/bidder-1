package main.services;


import main.entities.User;

import java.util.List;

public interface UserService {

    void addUser(User u);
    void updateUser(User u);
    List<User> listUsers();
    User getUserById(int id);
    void removeUser(int id);
}
