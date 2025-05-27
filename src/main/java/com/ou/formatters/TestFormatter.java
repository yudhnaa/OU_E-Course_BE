package com.ou.formatters;


import com.ou.pojo.Test;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class TestFormatter implements Formatter<Test> {
    @Override
    public Test parse(String text, Locale locale) throws ParseException {
        if(text != null && !text.trim().isEmpty()) {
            return new Test(Integer.parseInt(text));
        }
        return null;
    }

    @Override
    public String print(Test object, Locale locale) {
        if (object != null && object.getId() != null) {
            return String.valueOf(object.getId());
        }
        return "";
    }
}
