package main.controller;

import main.entities.User;
import main.modules.UserModuleApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/users")
public class UserController {

    private static Logger LOGGER = Logger.getLogger(UserController.class);

    private static final String USER_LIST = "userlist";
    private static final String REGISTER_PAGE = "registerpage";

    @Autowired
    private UserModuleApi userModule;

    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public ModelAndView userList() {
        ModelAndView model = new ModelAndView();
        model.addObject("users", userModule.getUserList());
        model.setViewName(USER_LIST);
        return model;
    }


    @RequestMapping(value = "registerPage", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("user", new User());
        model.setViewName(REGISTER_PAGE);
        return model;
    }
}
