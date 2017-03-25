package main.controller;

import main.dto.UserDto;
import main.services.UserService;
import main.validators.LoginValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MainPageController {

    private static Logger LOGGER = Logger.getLogger(MainPageController.class);

    private static final String MAIN_PAGE = "mainpage";
    private static final String LOGIN_PAGE = "loginPage";
    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    LoginValidator validator;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView helloWorld(HttpServletRequest request) {
        LOGGER.info("HelloWorld method invoked.");
        ModelAndView model = new ModelAndView();
        model.setViewName(MAIN_PAGE);
        return model;
    }

    @RequestMapping(value = "loginPage", method = RequestMethod.GET)
    public ModelAndView displayLoginPage(HttpServletRequest request, @ModelAttribute("user") UserDto user) {
        LOGGER.info("displayLoginPage method invoked.");
        ModelAndView model = new ModelAndView();
        model.setViewName(LOGIN_PAGE);
        return model;
    }

    @RequestMapping(value = "loginPage", method = RequestMethod.POST)
    public ModelAndView executeLogin(@ModelAttribute("user") UserDto user, BindingResult bindingResult, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        validator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            model.setViewName(LOGIN_PAGE);
        } else {
            try {
                request.login(user.getLogin(), user.getPassword());
                request.getSession().setAttribute("loggedUser", userService.transformUser(user, true));
                model.setViewName(MAIN_PAGE);
            } catch (ServletException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        return model;
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        try {
            request.logout();
            model.setViewName(MAIN_PAGE);
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return model;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
