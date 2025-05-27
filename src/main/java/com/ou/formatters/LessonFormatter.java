package com.ou.formatters;

import com.ou.pojo.Course;
import com.ou.pojo.Lesson;
import com.ou.services.CourseService;
import com.ou.services.LessonService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class LessonFormatter implements Formatter<Lesson> {
    @Autowired
    private LessonService lessonService;

    @Autowired
    LocalizationService localizationService;

    @Override
    public Lesson parse(String text, Locale locale) throws ParseException {
        try {
            Integer id = Integer.parseInt(text);
            Optional<Lesson> lesson = lessonService.getLessonById(id);
            if (lesson.isPresent()) {
                return lesson.get();
            } else {
                throw new ParseException(localizationService.getMessage("lesson.notFound", locale), 0);
            }
        } catch (NumberFormatException e) {
            throw new ParseException(localizationService.getMessage("lesson.invalidData.id", locale), 0);
        }
    }

    @Override
    public String print(Lesson lesson, Locale locale) {
        return (lesson != null && lesson.getId() != null) ? String.valueOf(lesson.getId()) : "";
    }
}
