package com.ou.formatters;

import com.ou.pojo.Exercise;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class ExerciseFormatter implements Formatter<Exercise> {
    @Override
    public Exercise parse(String text, Locale locale) throws ParseException {
        if (text != null && !text.trim().isEmpty()) {
            return new Exercise(Integer.parseInt(text));
        }
        return null;
    }

    @Override
    public String print(Exercise object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
