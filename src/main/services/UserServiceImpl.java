package main.services;

import main.dto.UserDto;
import main.entities.User;
import main.modules.UserModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserModule userModule;

    @Transactional
    public void registerUser(UserDto u) {
        this.userModule.saveUser(u);
    }

    @Transactional
    public List<User> listUsers() {
        return this.userModule.getUserList();
    }

    @Transactional
    public User getUserByLogin(String login) {
        return this.userModule.getUserByLogin(login);
    }

    public UserModule getUserModule() {
        return userModule;
    }

    public void setUserModule(UserModule userModule) {
        this.userModule = userModule;
    }

    @Override
    public boolean isValidUser(String login, String password) {
        return this.userModule.isValidUser(login, password);
    }

    @Override
    public User transformUser(UserDto userDto, boolean isValid) {
        return userModule.transformUser(userDto, isValid);
    }

}
