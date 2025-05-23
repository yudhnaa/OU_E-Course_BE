package com.ou.formatters;

import com.ou.pojo.Course;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

public class CourseFormatter implements Formatter<Course> {

    @Override
    public String print(Course course, Locale locale) {
        return String.valueOf(course.getId()); // Chuyển Course -> ID (String)
    }

    @Override
    public Course parse(String courseId, Locale locale) throws ParseException {
        Course c = new Course();
        c.setId(Integer.parseInt(courseId)); // Chuyển String ID -> Course
        return c;
    }
}