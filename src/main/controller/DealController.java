package main.controller;

import main.dto.DealDto;
import main.entities.User;
import main.services.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/deals")
public class DealController {

    private static final String DEAL = "deal";

    @Autowired
    DealService dealService;

    @RequestMapping(value = "/deal", method = RequestMethod.GET)
    public ModelAndView getDeal(@RequestParam String dealId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        DealDto deal = dealService.getDealById(dealId);
        model.addObject("deal", deal);
        model.addObject("results", dealService.getDealResultsByDealId(dealId));
        model.addObject("visible", dealService.isDealVisible(deal, ((User) request.getSession().getAttribute("loggedUser")).getLogin()));
        model.setViewName(DEAL);
        return model;
    }
}
