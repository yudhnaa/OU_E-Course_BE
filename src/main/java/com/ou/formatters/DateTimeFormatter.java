package com.ou.formatters;

import com.ou.pojo.Course;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;

@Component("dateTimeFormatter")
public class DateTimeFormatter {
    public String format(LocalDateTime dateTime, String pattern) {
        if (dateTime == null) {
            return null;
        }
        java.time.format.DateTimeFormatter formatter = java.time.format.DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

}
