package main.controller;

import main.entities.Tournament;
import main.services.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tournaments")
public class TournamentController {

    public static final String TOURNAMENT_LIST = "tournamentlist";
    public static final String TOURNAMENT = "tournament";

    @Autowired
    TournamentService tournamentService;

    @RequestMapping(value = "/tourlist", method = RequestMethod.GET)
    public ModelAndView getTournamentList() {
        ModelAndView model = new ModelAndView();
        model.addObject("tourlist", tournamentService.listTournament());
        model.setViewName(TOURNAMENT_LIST);
        return model;
    }

    @RequestMapping(value = "/tour", method = RequestMethod.GET)
    public ModelAndView getTournament(@RequestParam int tourId) {
        ModelAndView model = new ModelAndView();
        model.addObject("tour", tournamentService.getById(tourId));
        model.setViewName(TOURNAMENT);
        return model;
    }
}
