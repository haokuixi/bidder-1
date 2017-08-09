package main.controllers;

import main.dto.XmlContentDto;
import main.exceptions.XmlValidationException;
import main.services.JudgeService;
import main.services.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/judge")
public class JudgeToolsController {

    private static final String JUDGE_PAGE = "judge";

    @Autowired
    JudgeService judgeService;
    @Autowired
    MovementService movementService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getJudgePage(@ModelAttribute("file") XmlContentDto file) {
        ModelAndView model = new ModelAndView();
        model.addObject("file", new XmlContentDto());
        model.setViewName(JUDGE_PAGE);
        return model;
    }

    @RequestMapping(value = "import", method = RequestMethod.POST)
    public ModelAndView importFile(@ModelAttribute("file") XmlContentDto file, HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        model.setViewName(JUDGE_PAGE);
        try {
            judgeService.validateXml(file.getXmlContent());
            movementService.create(file);
            model.addObject("success", true);
        } catch (XmlValidationException e) {
            model.addObject("validationError", e.getMessage());
        }

        model.addObject("file", new XmlContentDto());
        return model;
    }
}
