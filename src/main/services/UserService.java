package main.services;


import main.dto.UserDto;
import main.entities.User;

import java.util.List;

public interface UserService {

    void registerUser(UserDto u);
    List<User> listUsers();
}
