package main.controller;

import main.entities.User;
import main.entities.Wzbs;
import main.modules.UserModuleApi;
import main.modules.WzbsModuleApi;
import main.services.UserService;
import main.services.WzbsService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private UserService userService;
    @Autowired
    private WzbsService wzbsService;

    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public ModelAndView userList() {
        ModelAndView model = new ModelAndView();
        model.addObject("users", userService.listUsers());
        model.setViewName(USER_LIST);
        return model;
    }


    @RequestMapping(value = "registerPage", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("user", new User());
        model.addObject("wzbsList", wzbsService.getAll());
        model.setViewName(REGISTER_PAGE);
        return model;
    }

    @RequestMapping(value = "registerPage", method = RequestMethod.POST)
    public ModelAndView registerPage(User user) {
        ModelAndView model = new ModelAndView();
        userService.addUser(user);
        model.setViewName(USER_LIST);
        return model;
    }
}
