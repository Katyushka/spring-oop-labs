package com.lab5.web.lab5;

import com.lab5.domain.FileForm;
import com.lab5.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Created by ekaterina on 02.04.2017.
 */

@Controller
@RequestMapping("/lab5")
public class Task1Controller {

    private static final Logger log = LoggerFactory.getLogger(Task1Controller.class);


    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/task1")
    public String getHomePage(Model model) {
        log.debug("Getting home page");
        model.addAttribute("form", new FileForm());
        return "lab5/task1";
    }


    @RequestMapping(value = "/task1", method = RequestMethod.POST)
    public String showUploadedText(@ModelAttribute("form") FileForm form, @RequestParam("file") MultipartFile file, BindingResult bindingResult, Model model) {

        model.addAttribute("form", form);
        try {
            form.setName(file.getOriginalFilename());
            form.setSize(file.getSize());
            form.setContent(file.getBytes());
            form.setMimeType(file.getContentType());
        } catch (IOException e) {
            e.printStackTrace();
        }


        fileService.save(form);

        String word = fileService.getLongestWord(form);

        return "lab5/task1";
    }


}
