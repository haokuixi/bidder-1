package main.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainPageController {

    private static Logger LOGGER = Logger.getLogger(MainPageController.class);

    private static final String MAIN_PAGE = "mainpage";

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String helloWorld() {
        LOGGER.info("HelloWorld method invoked.");
        return MAIN_PAGE;
    }

}
