package com.lab5.service;

import org.springframework.stereotype.Service;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by ekaterina on 02.04.2017.
 */

@Service
public class CalculateService {

    private static final String CALCULATE_RESULT = "${script}";
    private static final ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");

    public String getResultValue(String function) {
        function = CALCULATE_RESULT.replace("${script}", function);
        String result = calculateResult(function);
        return result;
    }


    private static String calculateResult(String function) {
        String resultVal = "";
        try {
            Object result = engine.eval(function);
            resultVal = result.toString();
        } catch (ScriptException e) {
            e.printStackTrace();
            return resultVal;
        }
        return resultVal;
    }


}
