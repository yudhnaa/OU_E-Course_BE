package com.ou.formatters;

import com.ou.pojo.Course;
import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

import com.ou.services.CourseService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class CourseFormatter implements Formatter<Course> {

    @Autowired
    private CourseService courseService;

    @Autowired
    LocalizationService localizationService;

    @Override
    public String print(Course course, Locale locale) {
        return (course != null && course.getId() != null) ? String.valueOf(course.getId()) : "";
    }

    Override
    public Course parse(String text, Locale locale) throws ParseException {
        try {
            Integer id = Integer.parseInt(text);
            Optional<Course> course = courseService.getCourseById(id);
            if (course.isPresent()) {
                return course.get();
            } else {
                throw new ParseException(localizationService.getMessage("course.notFound", locale), 0);
            }
        } catch (NumberFormatException e) {
            throw new ParseException(localizationService.getMessage("course.invalidData.Id", locale), 0);
        }
    }
}
