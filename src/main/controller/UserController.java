package main.controller;

import main.dto.UserDto;
import main.entities.User;
import main.services.TournamentService;
import main.services.UserService;
import main.services.WzbsService;
import main.validators.EditUserValidator;
import main.validators.UserValidator;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/users")
public class UserController {

    private static Logger LOGGER = Logger.getLogger(UserController.class);

    private static final String USER_LIST = "userlist";
    private static final String REGISTER_PAGE = "registerpage";
    private static final String EDIT_PROFILE = "editprofile";
    private static final String USER_PROFILE = "userprofile";
    private static final int FIRST_PAGE = 1;
    private static final double USER_PER_PAGE = 10;

    @Autowired
    UserValidator userValidator;
    @Autowired
    EditUserValidator editUserValidator;
    @Autowired
    private UserService userService;
    @Autowired
    private WzbsService wzbsService;
    @Autowired
    private TournamentService tournamentService;


    @RequestMapping(value = "/userlist", method = RequestMethod.GET)
    public ModelAndView userList(@RequestParam int page) {
        ModelAndView model = new ModelAndView();
        model.addObject("users", userService.listUsers(page));
        model.addObject("pages", (int) Math.ceil((double) userService.countUsers() / USER_PER_PAGE));
        model.addObject("page", page);
        model.setViewName(USER_LIST);
        return model;
    }


    @RequestMapping(value = "registerPage", method = RequestMethod.GET)
    public ModelAndView registerPage() {
        ModelAndView model = new ModelAndView();
        model.addObject("user", new UserDto());
        model.addObject("wzbsList", wzbsService.getAll());
        model.setViewName(REGISTER_PAGE);
        return model;
    }

    @RequestMapping(value = "registerPage", method = RequestMethod.POST)
    public ModelAndView registerPage(@ModelAttribute("user") UserDto user, BindingResult bindingResult, ModelAndView model) {
        this.userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addObject("wzbsList", wzbsService.getAll());
            model.setViewName(REGISTER_PAGE);
            return model;
        }

        userService.registerUser(user);
        model.addObject("users", userService.listUsers(FIRST_PAGE));
        model.setViewName(USER_LIST);
        return model;
    }

    @RequestMapping(value = "/editprofile", method = RequestMethod.GET)
    public ModelAndView editProfilePage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        User user = userService.getUserByLogin(((User) request.getSession().getAttribute("loggedUser")).getLogin());
        model.addObject("user", userService.transformUser(user));
        model.addObject("wzbsList", wzbsService.getAll());
        model.setViewName(EDIT_PROFILE);
        return model;
    }

    @RequestMapping(value = "/editprofile", method = RequestMethod.POST)
    public ModelAndView editProfilePage(@ModelAttribute("user") UserDto user, BindingResult bindingResult,
                                        ModelAndView model, HttpServletRequest request) {

        this.editUserValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addObject("wzbsList", wzbsService.getAll());
            model.setViewName(EDIT_PROFILE);
            return model;
        }

        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        userService.updateUser(loggedUser, user);
        model.addObject("user", userService.transformUser(userService.getUserByLogin(loggedUser.getLogin())));
        model.addObject("wzbsList", wzbsService.getAll());
        model.setViewName(EDIT_PROFILE);
        return model;
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ModelAndView getUserProfile(@RequestParam int userId) {
        ModelAndView model = new ModelAndView();
        User user = userService.getUserById(userId);
        model.addObject("user", user);

        if (user.isJudge()) {
            model.addObject("tours", tournamentService.getByJudge(userId));
        } else {
            model.addObject("tours", tournamentService.getByPlayer(userId));
        }

        model.setViewName(USER_PROFILE);
        return model;
    }
}
