package main.controller;

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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getDeal(@RequestParam String dealId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();

        return model;
    }
}
