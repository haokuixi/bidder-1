package main.modules;

import main.entities.User;

import java.util.List;

public interface UserModuleApi {
    List<User> getUserList();
    void saveUser(User user);
}
