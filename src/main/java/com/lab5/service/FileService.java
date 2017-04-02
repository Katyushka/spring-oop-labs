package com.lab5.service;

import com.lab5.domain.FileForm;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Created by ekaterina on 28.03.2017.
 */


@Service
public class FileService {

    private String longestWord;

    public String getLongestWord(FileForm form) {

        try {
            List<String> lines = Files.readAllLines(Paths.get("output.txt"), StandardCharsets.UTF_8);


            String maxLength = "";
            List<String> words = new ArrayList<>();
            for (int i = 0; i < lines.size(); i++) {
                List<String> list = Arrays.asList(lines.get(i).split(" |\\.|\\!|\\?|\\:|\\;"));
                words.addAll(list);
            }



            for (int i = 0; i < words.size(); i++) {
                if (words.get(i).length() > maxLength.length()) maxLength = words.get(i);
            }

            final String maxWord = maxLength;

            lines.forEach(line ->
                    line.replace(maxWord, "")
            );

            PrintWriter pw = null;
            try {
                pw = new PrintWriter("output.txt");
                for (int i =0; i<lines.size();i++)
                    pw.print(lines);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        /*
        String[] maxLength = {""};
        lines.forEach(line -> {
            List<String> list = Arrays.asList(line.split(" |\\.|\\!|\\?|\\:|\\;"));
            list.forEach(word -> {
            });
        });
        */

            int len = maxLength.length();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return longestWord;
    }


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
