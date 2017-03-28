package com.lab5.service;

import com.lab5.domain.FileForm;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by ekaterina on 28.03.2017.
 */


@Service
public class FileService {

    public void save(FileForm form) {
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream("output.txt");
            fs.write(form.getContent());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
