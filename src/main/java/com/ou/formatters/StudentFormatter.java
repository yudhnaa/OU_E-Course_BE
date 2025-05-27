package com.ou.formatters;

import com.ou.pojo.Exercise;
import com.ou.pojo.Student;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class StudentFormatter implements Formatter<Student> {
    @Override
    public Student parse(String text, Locale locale) throws ParseException {
        if (text != null && !text.trim().isEmpty()) {
            return new Student(Integer.parseInt(text));
        }
        return null;
    }

    @Override
    public String print(Student object, Locale locale) {
        return String.valueOf(object.getId());
    }
}
