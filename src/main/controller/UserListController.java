package main.controller;

import main.modules.UserModuleApi;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/users")
public class UserListController {

    private static Logger LOGGER = Logger.getLogger(UserListController.class);

    private static final String USER_LIST = "userlist";

    @Autowired
    private UserModuleApi userModule;

    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public String userList() {
        LOGGER.info("userList method invoked.");
        return USER_LIST;
    }

}
