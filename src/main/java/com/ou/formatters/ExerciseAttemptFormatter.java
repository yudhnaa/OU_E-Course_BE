package com.ou.formatters;

import com.ou.pojo.ExerciseAttempt;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class ExerciseAttemptFormatter implements Formatter<ExerciseAttempt> {
    @Override
    public ExerciseAttempt parse(String text, Locale locale) throws ParseException {
        if(text != null && !text.trim().isEmpty()){
            return new ExerciseAttempt(Integer.parseInt(text));
        }
        return null;
    }

    @Override
    public String print(ExerciseAttempt object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
