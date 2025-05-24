package com.ou.formatters;

import com.ou.pojo.Lesson;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class LessonFormatter implements Formatter<Lesson> {
    @Override
    public String print(Lesson lesson, Locale locale) {
        return String.valueOf(lesson.getId()); // Convert Lesson -> ID (String)
    }

    @Override
    public Lesson parse(String lessonId, Locale locale) throws ParseException {
        Lesson lesson = new Lesson();
        lesson.setId(Integer.parseInt(lessonId)); // Convert String ID -> Lesson
        return lesson;
    }
}
