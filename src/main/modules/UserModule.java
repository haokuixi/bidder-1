package main.modules;

import main.dto.UserDto;
import main.entities.User;

import java.util.List;

public interface UserModule {
    List<User> getUserList();
    void saveUser(UserDto user);
}
