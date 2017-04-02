package com.lab5.web;

import com.lab5.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;

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
        return "index";
    }


}
