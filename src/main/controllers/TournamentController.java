package main.controllers;

import main.dto.MovementDto;
import main.dto.TournamentDto;
import main.dto.TournamentMode;
import main.entities.User;
import main.services.PairService;
import main.services.RoundService;
import main.services.TournamentService;
import main.services.UserService;
import main.validators.EditTournamentValidator;
import main.validators.TournamentValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    private static final String TOURNAMENT_LIST = "tournamentlist";
    private static final String TOURNAMENT = "tournament";
    private static final String CREATE_TOURNAMENT = "createtournament";
    private static final String EDIT_TOURNAMENT = "edittournament";
    private static final double TOURS_PER_PAGE = 10;

    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private UserService userService;
    @Autowired
    private TournamentValidator tournamentValidator;
    @Autowired
    private PairService pairService;
    @Autowired
    private EditTournamentValidator editTournamentValidator;
    @Autowired
    private RoundService roundService;


    @RequestMapping(value = "/tourlist", method = RequestMethod.GET)
    public ModelAndView getTournamentList(@RequestParam int page) {
        ModelAndView model = new ModelAndView();
        model.addObject("tourlist", tournamentService.listTournament(page));
        model.addObject("pages", (int) Math.ceil((double) tournamentService.countTours() / TOURS_PER_PAGE));
        model.addObject("page", page);
        model.setViewName(TOURNAMENT_LIST);
        return model;
    }

    @RequestMapping(value = "/tour", method = RequestMethod.GET)
    public ModelAndView getTournament(@RequestParam String tourId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("canJoin", tournamentService.canUserJoinTournament(tourId,
                ((User) request.getSession().getAttribute("loggedUser")).getId()));
        model.addObject("tour", tournamentService.getByHashedId(tourId));
        model.addObject("awaiting", userService.getAwaitingByTournament(tourId));
        model.setViewName(TOURNAMENT);
        return model;
    }

    @RequestMapping(value = "/tour", method = RequestMethod.POST, params = {"startDate", "!endDate", "!quit", "!enter", "!quitPair", "!enterPair", "!startRound"})
    public ModelAndView beginTournament(HttpServletRequest request) {
        String tourId = request.getParameter("tourId");
        ModelAndView model = new ModelAndView();

        MovementDto movements = tournamentService.checkTournamentBeforeBegin(tourId);

        if (movements == null) {
            model.addObject("error", "label.tournament.cantstart");
        } else {
            tournamentService.beginTournament(tourId, movements);
            model.addObject("error", "none");
        }

        model.addObject("tour", tournamentService.getByHashedId(tourId));
        model.setViewName(TOURNAMENT);
        return model;
    }

    @RequestMapping(value = "/tour", method = RequestMethod.POST, params = {"endDate", "!startDate", "!quit", "!enter", "!quitPair", "!enterPair", "!startRound"})
    public ModelAndView setEndDate(HttpServletRequest request) {
        String tourId = request.getParameter("tourId");

        tournamentService.setTournamentEndDate(tourId, LocalDateTime.now());

        ModelAndView model = new ModelAndView();
        model.addObject("tour", tournamentService.getByHashedId(tourId));
        model.setViewName(TOURNAMENT);
        return model;
    }

    @RequestMapping(value = "/tour", method = RequestMethod.POST, params = {"!startDate", "!endDate", "quit", "!enter", "!quitPair", "!enterPair", "!startRound"})
    public ModelAndView quitFromTournament(HttpServletRequest request) {
        String tourId = request.getParameter("tourId");

        userService.quitFromTournament(((User) request.getSession().getAttribute("loggedUser")).getLogin(), tourId);

        ModelAndView model = new ModelAndView();
        model.addObject("canJoin", tournamentService.canUserJoinTournament(tourId,
                ((User) request.getSession().getAttribute("loggedUser")).getId()));
        model.addObject("tour", tournamentService.getByHashedId(tourId));
        model.addObject("awaiting", userService.getAwaitingByTournament(tourId));
        model.setViewName(TOURNAMENT);
        return model;
    }

    @RequestMapping(value = "/tour", method = RequestMethod.POST, params = {"!startDate", "!endDate", "!quit", "enter", "!quitPair", "!enterPair", "!startRound"})
    public ModelAndView enterIntoTournament(HttpServletRequest request) {
        String tourId = request.getParameter("tourId");

        userService.enterIntoTournament(((User) request.getSession().getAttribute("loggedUser")).getLogin(), tourId);

        ModelAndView model = new ModelAndView();
        model.addObject("tour", tournamentService.getByHashedId(tourId));
        model.addObject("awaiting", userService.getAwaitingByTournament(tourId));
        model.setViewName(TOURNAMENT);
        return model;
    }

    @RequestMapping(value = "/tour", method = RequestMethod.POST, params = {"!startDate", "!endDate", "!quit", "!enter", "quitPair", "!enterPair", "!startRound"})
    public ModelAndView quitTournamentWithPair(HttpServletRequest request) {
        String tourId = request.getParameter("tourId");

        userService.quitWithPair(((User) request.getSession().getAttribute("loggedUser")).getLogin(), tourId);

        ModelAndView model = new ModelAndView();
        model.addObject("canJoin", tournamentService.canUserJoinTournament(tourId,
                ((User) request.getSession().getAttribute("loggedUser")).getId()));
        model.addObject("tour", tournamentService.getByHashedId(tourId));
        model.addObject("awaiting", userService.getAwaitingByTournament(tourId));
        model.setViewName(TOURNAMENT);
        return model;
    }

    @RequestMapping(value = "/tour", method = RequestMethod.POST, params = {"!startDate", "!endDate", "!quit", "!enter", "!quitPair", "enterPair", "!startRound"})
    public ModelAndView enterTournamentWithAwaiting(HttpServletRequest request) {
        String tourId = request.getParameter("tourId");
        int otherPlayer = Integer.parseInt(request.getParameter("otherPlayer"));
        userService.quitFromTournament(userService.getUserById(otherPlayer).getLogin(), tourId); // remove from awaiting
        pairService.createPair(((User) request.getSession().getAttribute("loggedUser")).getId(), otherPlayer, tourId);

        ModelAndView model = new ModelAndView();
        model.addObject("canJoin", tournamentService.canUserJoinTournament(tourId,
                ((User) request.getSession().getAttribute("loggedUser")).getId()));
        model.addObject("tour", tournamentService.getByHashedId(tourId));
        model.addObject("awaiting", userService.getAwaitingByTournament(tourId));
        model.setViewName(TOURNAMENT);
        return model;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView createTournamentPage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("tour", new TournamentDto());
        model.addObject("modes", Arrays.asList(TournamentMode.class.getEnumConstants()));
        model.setViewName(CREATE_TOURNAMENT);
        return model;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createTournament(@ModelAttribute("tour") TournamentDto tour, BindingResult bindingResult,
                                         ModelAndView model, HttpServletRequest request) {

        tour.setStartDate(tour.getStartDate().concat(" ").concat(tour.getStartTime()));
        this.tournamentValidator.validate(tour, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addObject("modes", Arrays.asList(TournamentMode.class.getEnumConstants()));
            model.setViewName(CREATE_TOURNAMENT);
            return model;
        }

        tour.setJudge((User) request.getSession().getAttribute("loggedUser"));
        tournamentService.addTournament(tour);
        model.addObject("tourlist", tournamentService.listTournament(1));
        model.addObject("pages", (int) Math.ceil((double) tournamentService.countTours() / TOURS_PER_PAGE));
        model.addObject("page", 1);
        model.setViewName(TOURNAMENT_LIST);
        return model;
    }

    @RequestMapping(value = "/tour/edit", method = RequestMethod.GET)
    public ModelAndView editTournamentPage(@RequestParam(value = "tourId") String tourId) {
        ModelAndView model = new ModelAndView();
        model.addObject("tour", tournamentService.getByHashedId(tourId));
        model.addObject("modes", Arrays.asList(TournamentMode.class.getEnumConstants()));
        model.setViewName(EDIT_TOURNAMENT);
        return model;
    }

    @RequestMapping(value = "/tour/edit", method = RequestMethod.POST)
    public ModelAndView editTournamentPage(@RequestParam String tourId, @ModelAttribute("tour") TournamentDto tour,
                                           BindingResult bindingResult, ModelAndView model) {

        this.editTournamentValidator.validate(tour, bindingResult);

        if (bindingResult.hasErrors()) {
            tour.setStatus(tournamentService.getByHashedId(tourId).getStatus());
            model.addObject("modes", Arrays.asList(TournamentMode.class.getEnumConstants()));
            model.setViewName(EDIT_TOURNAMENT);
            return model;
        }

        tournamentService.editTournament(tourId, tour);
        model.addObject("tour", tournamentService.getByHashedId(tourId));
        model.setViewName(TOURNAMENT);
        return model;
    }
}
