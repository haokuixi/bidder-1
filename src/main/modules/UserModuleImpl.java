package main.modules;

import main.dao.UserDAO;
import main.dto.UserDto;
import main.dto.WzbsDto;
import main.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class UserModuleImpl implements UserModule {

    private static final String WZBS_NZ = "NZ";
    private static final String ROLE_USER = "ROLE_USER";
    private static final int USER_PER_PAGE = 10;
    UserDAO userDAO;
    WzbsModule wzbsModule;

    @Override
    public List<User> getUserList(int page) {
        List<User> allUsers = userDAO.listAll();
        Collator collator = Collator.getInstance(Locale.ENGLISH);
        if (!allUsers.isEmpty()) {
            Collections.sort(allUsers, (u1, u2) -> collator.compare(u1.getLogin(), u2.getLogin()));
        }

        if (USER_PER_PAGE * page <= allUsers.size()) {
            int from = USER_PER_PAGE * (page - 1) + 1;
            int to = USER_PER_PAGE * page;
            return allUsers.subList(from, to);
        } else if (USER_PER_PAGE * (page - 1) < allUsers.size()) {
            int from = USER_PER_PAGE * (page - 1) + 1;
            int to = allUsers.size() - 1;
            return allUsers.subList(from, to);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public void saveUser(UserDto user) {
        userDAO.create(transformUser(user, false));
    }

    @Override
    public void updateUser(User loggedUser, UserDto user) {
        if (!loggedUser.getName().equals(user.getFirstName())) {
            loggedUser.setName(user.getFirstName());
        }
        if (!loggedUser.getSurname().equals(user.getLastName())) {
            loggedUser.setSurname(user.getLastName());
        }
        userDAO.update(loggedUser);
    }

    @Override
    public Long countUsers() {
        return userDAO.countUsers();
    }

    @Override
    public User getById(int id) {
        return userDAO.getById(id);
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public WzbsModule getWzbsModule() {
        return wzbsModule;
    }

    public void setWzbsModule(WzbsModule wzbsModule) {
        this.wzbsModule = wzbsModule;
    }

    public User transformUser(UserDto userDto, boolean isValid) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User u;

        if (isValid) {
            u = getUserByLogin(userDto.getLogin());
        } else {
            u = new User();
            u.setLogin(userDto.getLogin());
            u.setName(userDto.getFirstName());
            u.setSurname(userDto.getLastName());
            u.setPassword(passwordEncoder.encode(userDto.getPassword()));
            u.setWzbs(wzbsModule.getWzbsByShortName(userDto.getWzbs().getShortName()));
            u.setJudge(userDto.isJudge());
            u.setRole(ROLE_USER);

            if (!WZBS_NZ.equals(u.getWzbs().getShortName())) {
                u.setPzbsId(userDto.getPzbsId());
            }
        }
        return u;
    }

    public UserDto transformUser(User user) {
        UserDto u = new UserDto();
        u.setLogin(user.getLogin());
        u.setPassword(user.getPassword());
        u.setFirstName(user.getName());
        u.setLastName(user.getSurname());
        u.setRole(user.getRole());
        u.setWzbs(new WzbsDto(user.getWzbs().getShortName()));
        u.setPzbsId(user.getPzbsId());
        return u;
    }

    @Override
    public User getUserByLogin(String login) {
        return userDAO.getUserByLogin(login);
    }

    @Override
    public boolean isValidUser(String login, String password) {
        return userDAO.isValidUser(login, password);
    }


}
