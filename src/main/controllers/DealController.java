package main.controllers;

import main.dto.DealDto;
import main.dto.DealResultDto;
import main.dto.EnteredDealDto;
import main.entities.User;
import main.exceptions.EnterDealValidationException;
import main.exceptions.LeadValidationException;
import main.services.DealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/deals")
public class DealController {

    private static final String DEAL = "deal";
    private static final String ENTER_RESULT = "enterresult";
    private static final String ENTER_DEAL = "enterdeal";

    @Autowired
    DealService dealService;

    @RequestMapping(value = "/deal", method = RequestMethod.GET)
    public ModelAndView getDeal(@RequestParam String dealId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        setDealModelObjects(model, dealId, request);
        model.setViewName(DEAL);
        return model;
    }

    @RequestMapping(value = "/enterresult", method = RequestMethod.GET)
    public ModelAndView enterResultView(@RequestParam String dealId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("dealId", dealId);
        model.setViewName(ENTER_RESULT);
        return model;
    }

    @RequestMapping(value = "/enterresult", method = RequestMethod.POST, params = {"enterResult"})
    public ModelAndView enterResult(@RequestParam String dealId, HttpServletRequest request) {

        ModelAndView model = new ModelAndView();
        DealResultDto dto = new DealResultDto();
        dto.setLead(request.getParameter("leadValue"));

        try {
            dealService.validateDealResult(dto);
            dealService.saveDealResult((User) request.getSession().getAttribute("loggedUser"),
                    request.getParameter("colorValue"),
                    Integer.valueOf(request.getParameter("heightValue")),
                    Integer.valueOf(request.getParameter("tricksValue")),
                    Integer.valueOf(request.getParameter("doubleValue")),
                    request.getParameter("positionValue"),
                    Boolean.valueOf(request.getParameter("vulnerableValue")),
                    dealId,
                    request.getParameter("leadValue"));
            model.addObject("success", true);
        } catch (LeadValidationException e) {
            model.addObject("validationError", e.getMessage());
            model.addObject("dealId", dealId);
            model.setViewName(ENTER_RESULT);
            return model;
        }

        setDealModelObjects(model, dealId, request);
        model.setViewName(DEAL);
        return model;
    }


    @RequestMapping(value = "/enterdeal", method = RequestMethod.GET)
    public ModelAndView getEnterDeal(@RequestParam String dealId, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.addObject("dealId", dealId);
        model.addObject("deal", new EnteredDealDto());
        model.setViewName(ENTER_DEAL);
        return model;
    }

    private void setDealModelObjects(ModelAndView model, String dealId, HttpServletRequest request) {
        DealDto deal = dealService.getDealById(dealId);
        User loggedUser = (User) request.getSession().getAttribute("loggedUser");
        model.addObject("deal", deal);
        model.addObject("results", dealService.getDealResultsByDealId(dealId));
        model.addObject("canEnterDeal", dealService.canEnterDeal(dealId, loggedUser));
        model.addObject("visible", dealService.isDealVisible(deal, (loggedUser).getLogin()));
        model.addObject("resultsVisible", dealService.areResultsVisible(dealId, loggedUser));
        model.addObject("buttonVisible", dealService.isEnterResultButtonVisible(deal, (loggedUser).getLogin()));
    }


    @RequestMapping(value = "/enterdeal", method = RequestMethod.POST)
    public ModelAndView enterDeal(@ModelAttribute("deal") EnteredDealDto deal, ModelAndView model, HttpServletRequest request) {
        String dealId = request.getParameter("dealId");

        try {
            dealService.enterDeal(dealId, deal);
        } catch (EnterDealValidationException e) {
            model.addObject("error", e.getMessage());
            model.setViewName(ENTER_DEAL);
            return model;
        }

        setDealModelObjects(model, dealId, request);
        model.setViewName(DEAL);
        return model;
    }
}
