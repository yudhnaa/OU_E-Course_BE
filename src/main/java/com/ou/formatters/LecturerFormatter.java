package com.ou.formatters;

import com.ou.pojo.Lecturer;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class LecturerFormatter implements Formatter<Lecturer> {

    @Override
    public String print(Lecturer lecturer, Locale locale) {
        return String.valueOf(lecturer.getId()); // Convert Lecturer to ID (String)
    }

    @Override
    public Lecturer parse(String lecturerId, Locale locale) throws ParseException {
        Lecturer lecturer = new Lecturer();
        lecturer.setId(Integer.parseInt(lecturerId)); // Convert String ID to Lecturer
        return lecturer;
    }
}
