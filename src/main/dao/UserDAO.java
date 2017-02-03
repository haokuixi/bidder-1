package main.dao;

import main.entities.User;

import java.util.List;

public interface UserDAO{

    void create(User u);
    User getById(int id);
    User update(User u);
    void remove(int id);
    List<User> listAll();
}
