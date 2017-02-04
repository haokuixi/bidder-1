package main.modules;

import main.entities.User;

import java.util.List;

public interface UserModule {
    List<User> getUserList();
    void saveUser(User user);
}
