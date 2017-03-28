package com.lab5.web;

import com.lab5.domain.FileForm;
import com.lab5.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

/**
 * Created by ekaterina on 27.03.2017.
 */

@Controller
public class IndexController implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
    protected static final String PATH_ROOT = "/";


    @Autowired
    private FileService fileService;

    @RequestMapping(PATH_ROOT)
    public String getHomePage(Model model) {
        LOGGER.debug("Getting home page");
        model.addAttribute("form", new FileForm());
        return "index";
    }


    @RequestMapping(value = PATH_ROOT, method = RequestMethod.POST)
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

        return "index";
    }


}
