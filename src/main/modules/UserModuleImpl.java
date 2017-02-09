package main.modules;

import main.dao.UserDAO;
import main.dto.UserDto;
import main.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class UserModuleImpl implements UserModule {

    private static final String WZBS_NZ = "NZ";

    UserDAO userDAO;
    WzbsModule wzbsModule;

    @Override
    public List<User> getUserList() {
        return userDAO.listAll();
    }

    @Override
    public void saveUser(UserDto user) {
        userDAO.create(transformUser(user));
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

    private User transformUser(UserDto userDto) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User u = new User();

        u.setLogin(userDto.getLogin());
        u.setName(userDto.getFirstName());
        u.setSurname(userDto.getLastName());
        u.setPassword(passwordEncoder.encode(userDto.getPassword()));
        u.setWzbs(wzbsModule.getWzbsByShortName(userDto.getWzbs().getShortName()));

        if(!WZBS_NZ.equals(u.getWzbs().getShortName())) {
            u.setPzbsId(userDto.getPzbsId());
        }

        return u;
    }
}
