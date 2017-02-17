package main.modules;

import main.dao.UserDAO;
import main.dto.UserDto;
import main.entities.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserModuleImpl implements UserModule {

    private static final String WZBS_NZ = "NZ";
    private static final String ROLE_USER = "ROLE_USER";
    UserDAO userDAO;
    WzbsModule wzbsModule;

    @Override
    public List<User> getUserList() {
        return userDAO.listAll();
    }

    @Override
    public void saveUser(UserDto user) {
        userDAO.create(transformUser(user, false));
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

        if(isValid) {
            u = getUserByLogin(userDto.getLogin());
        } else {
            u = new User();
            u.setLogin(userDto.getLogin());
            u.setName(userDto.getFirstName());
            u.setSurname(userDto.getLastName());
            u.setPassword(passwordEncoder.encode(userDto.getPassword()));
            u.setWzbs(wzbsModule.getWzbsByShortName(userDto.getWzbs().getShortName()));
            u.setRole(ROLE_USER);

            if(!WZBS_NZ.equals(u.getWzbs().getShortName())) {
                u.setPzbsId(userDto.getPzbsId());
            }
        }
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
