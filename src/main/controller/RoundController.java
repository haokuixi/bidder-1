package main.controller;

import main.entities.User;
import main.services.RoundService;
import main.services.TournamentService;
import main.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/rounds")
public class RoundController {

    private static final String TOURNAMENT = "tournament";
    private static final String ROUND = "round";

    @Autowired
    private RoundService roundService;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/round", method = RequestMethod.GET)
    public ModelAndView getRound(@RequestParam String roundId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("round", roundService.getById(roundId));
        model.addObject("tour", tournamentService.getByRoundId(roundId));
        model.setViewName(ROUND);
        return model;
    }


    @RequestMapping(value = "/tour", method = RequestMethod.POST, params = {"!startDate", "!endDate", "!quit", "!enter", "!quitPair", "!enterPair", "startRound"})
    public ModelAndView startRound(HttpServletRequest request) {
        String tourId = request.getParameter("tourId");

        tournamentService.beginNextRound(tourId);

        ModelAndView model = new ModelAndView();
        model.addObject("canJoin", tournamentService.canUserJoinTournament(tourId,
                ((User) request.getSession().getAttribute("loggedUser")).getId()));
        model.addObject("tour", tournamentService.getByHashedId(tourId));
        model.addObject("awaiting", userService.getAwaitingByTournament(tourId));
        model.setViewName(TOURNAMENT);
        return model;
    }
}