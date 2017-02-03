package main.services;


import main.entities.User;

import java.util.List;

public interface UserService {

    void addUser(User u);
    List<User> listUsers();
}
