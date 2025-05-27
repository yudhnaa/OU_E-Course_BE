package com.ou.formatters;

import com.ou.pojo.ExerciseScoreStatus;
import org.springframework.format.Formatter;

import java.util.Locale;

public class ExerciseScoreStatusFormatter implements Formatter<ExerciseScoreStatus> {
    @Override
    public ExerciseScoreStatus parse(String text, Locale locale) {
        if (text == null || text.isEmpty()) return null;
        return new ExerciseScoreStatus(Integer.parseInt(text));
    }

    @Override
    public String print(ExerciseScoreStatus object, Locale locale) {
        return (object != null && object.getId() != null) ? String.valueOf(object.getId()) : "";
    }
}

