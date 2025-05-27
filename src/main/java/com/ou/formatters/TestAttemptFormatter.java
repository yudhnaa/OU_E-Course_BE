package com.ou.formatters;

import com.ou.pojo.TestAttempt;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class TestAttemptFormatter implements Formatter<TestAttempt> {

    @Override
    public TestAttempt parse(String text, Locale locale) throws ParseException {
        if(text != null && !text.trim().isEmpty()) {
            return new TestAttempt(Integer.parseInt(text));
        }
        return null;
    }

    @Override
    public String print(TestAttempt object, Locale locale) {
        if (object != null && object.getId() != null) {
            return String.valueOf(object.getId());
        }
        return "";
    }
}
