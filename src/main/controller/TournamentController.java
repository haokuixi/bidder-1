package main.controller;

import main.dto.TournamentDto;
import main.dto.TournamentMode;
import main.entities.User;
import main.services.TournamentService;
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

    public static final String TOURNAMENT_LIST = "tournamentlist";
    public static final String TOURNAMENT = "tournament";
    public static final String CREATE_TOURNAMENT = "createtournament";
    public static final String EDIT_TOURNAMENT = "edittournament";
    public static final double TOURS_PER_PAGE = 10;

    @Autowired
    TournamentService tournamentService;
    @Autowired
    TournamentValidator tournamentValidator;

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
    public ModelAndView getTournament(@RequestParam(value = "tourId") int tourId) {
        ModelAndView model = new ModelAndView();
        model.addObject("tour", tournamentService.getById(tourId));
        model.setViewName(TOURNAMENT);
        return model;
    }

    @RequestMapping(value = "/tour", method = RequestMethod.POST, params = {"startDate", "!endDate"})
    public ModelAndView setStartDate(HttpServletRequest request) {
        int tourId = Integer.parseInt(request.getParameter("tourId"));

        tournamentService.setTournamentStartDate(tourId, LocalDateTime.now());

        ModelAndView model = new ModelAndView();
        model.addObject("tour", tournamentService.getById(tourId));
        model.setViewName(TOURNAMENT);
        System.out.println("start kontr");
        return model;
    }

    @RequestMapping(value = "/tour", method = RequestMethod.POST, params = {"endDate", "!startDate"})
    public ModelAndView setEndDate(HttpServletRequest request) {
        int tourId = Integer.parseInt(request.getParameter("tourId"));

        tournamentService.setTournamentEndDate(tourId, LocalDateTime.now());

        ModelAndView model = new ModelAndView();
        model.addObject("tour", tournamentService.getById(tourId));
        model.setViewName(TOURNAMENT);
        System.out.println("end kontr");
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
        model.setViewName(TOURNAMENT_LIST);
        return model;
    }

    @RequestMapping(value = "/tour/edit", method = RequestMethod.GET)
    public ModelAndView editTournamentPage(@RequestParam(value = "tourId") int tourId) {
        ModelAndView model = new ModelAndView();
        model.addObject("tour", tournamentService.getById(tourId));
        model.setViewName(EDIT_TOURNAMENT);
        return model;
    }

    @RequestMapping(value = "/tour/edit", method = RequestMethod.POST)
    public ModelAndView editTournamentPage(@RequestParam int tourId, @ModelAttribute("tour") TournamentDto tour,
                                           BindingResult bindingResult, ModelAndView model, HttpServletRequest request) {
        model.addObject("tour", tournamentService.getById(tourId));
        model.addObject("modes", Arrays.asList(TournamentMode.class.getEnumConstants()));
        model.setViewName(EDIT_TOURNAMENT);
        return model;
    }

}
