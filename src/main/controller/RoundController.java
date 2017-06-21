package main.controller;

import main.dto.RoundStatus;
import main.dto.TournamentDto;
import main.entities.Pair;
import main.entities.Round;
import main.entities.User;
import main.services.PairService;
import main.services.RoundResultService;
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
    @Autowired
    private PairService pairService;
    @Autowired
    private RoundResultService roundResultService;

    @RequestMapping(value = "/round", method = RequestMethod.GET)
    public ModelAndView getRound(@RequestParam String roundId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        TournamentDto tour = tournamentService.getByRoundId(roundId);
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        if (!loggedUser.isJudge() && tour.containsPlayer(loggedUser.getLogin())) {
            Pair pair = pairService.getByPlayerAndTour(loggedUser.getLogin(), tour.getHashedId());
            tour.setCurrentPair(pair);
        }

        Round round = roundService.getById(roundId);
        model.addObject("round", round);
        model.addObject("tour", tour);
        model.addObject("mode", tour.getTournamentMode().getName());
        model.addObject("roundResults", roundResultService.getByTourAndRoundNumber(tour.getHashedId(), round.getRoundNumber()));
        model.setViewName(ROUND);
        return model;
    }


    @RequestMapping(value = "/tour", method = RequestMethod.POST, params = {"!startDate", "!endDate", "!quit", "!enter", "!quitPair", "!enterPair", "startRound", "!completeRound"})
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

    @RequestMapping(value = "/tour", method = RequestMethod.POST, params = {"!startDate", "!endDate", "!quit", "!enter", "!quitPair", "!enterPair", "!startRound", "completeRound"})
    public ModelAndView completeRound(HttpServletRequest request) {
        String tourId = request.getParameter("tourId");

        tournamentService.completeRound(tourId);

        ModelAndView model = new ModelAndView();
        model.addObject("canJoin", tournamentService.canUserJoinTournament(tourId,
                ((User) request.getSession().getAttribute("loggedUser")).getId()));
        model.addObject("tour", tournamentService.getByHashedId(tourId));
        model.addObject("awaiting", userService.getAwaitingByTournament(tourId));
        model.setViewName(TOURNAMENT);
        return model;
    }
}
