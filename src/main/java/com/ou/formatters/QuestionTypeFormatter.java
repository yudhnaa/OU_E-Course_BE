package com.ou.formatters;

import com.ou.pojo.QuestionType;
import com.ou.services.QuestionTypeService;
import java.text.ParseException;
import java.util.Locale;

import com.ou.services.QuestionTypeService;
import org.springframework.format.Formatter;

public class QuestionTypeFormatter implements Formatter<QuestionType> {

    @Override
    public QuestionType parse(String text, Locale locale) throws ParseException {
        if (text != null && !text.trim().isEmpty()) {
            return new QuestionType(Integer.parseInt(text));
        }
        return null;
    }

    @Override
    public String print(QuestionType object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
