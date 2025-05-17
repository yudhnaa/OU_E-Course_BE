package com.ou.services;

import com.ou.pojo.CourseRate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CourseRateService {
    // Create operation with validation
    CourseRate addCourseRate(CourseRate courseRate) throws Exception;

    // Read operations with pagination
    List<CourseRate> getCourseRates(Map<String, String> params);
    List<CourseRate> searchCourseRates(Map<String, String> filters, Map<String, String> params);

    Optional<CourseRate> getCourseRateById(Integer id);
    List<CourseRate> getCourseRatesByCourse(Integer courseId, Map<String, String> params);
    List<CourseRate> getCourseRatesByStudent(Integer studentId, Map<String, String> params);

    // Update operation with validation
    CourseRate updateCourseRate(CourseRate courseRate) throws Exception;

    // Delete operation with validation
    boolean deleteCourseRate(Integer id) throws Exception;

    // Count methods for pagination
    long countCourseRates();
    long countCourseRatesByCourse(Integer courseId);
    long countCourseRatesByStudent(Integer studentId);
    long countSearchResults(Map<String, String> filters);
    
    // Validation methods
    boolean isValidRate(double rate);
    boolean studentCanRateCourse(Integer studentId, Integer courseId);
}
