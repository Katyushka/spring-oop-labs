package com.lab5.web.lab5;

import com.lab5.domain.CalculatorForm;
import com.lab5.service.CalculateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ekaterina on 02.04.2017.
 */


@Controller
@RequestMapping("lab5")
public class Task2Controller {

    private static final Logger log = LoggerFactory.getLogger(Task2Controller.class);

    @Autowired
    private CalculateService calculateService;

    @RequestMapping(value = "/task2")
    public String getHomePage(Model model) {
        log.debug("Getting home page");
        model.addAttribute("form", new CalculatorForm());
        return "lab5/task2";
    }


    @RequestMapping(value = "/task2", method = RequestMethod.POST, params = "calculate")
    public String generateData(@ModelAttribute("form") CalculatorForm form, BindingResult bindingResult, Model model) {
        String value = calculateService.getResultValue(form.getResult());
        form.setResult(value);
        model.addAttribute("form", form);
        return "lab5/task2";
    }

}
