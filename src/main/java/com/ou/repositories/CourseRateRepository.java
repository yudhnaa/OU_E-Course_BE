package com.ou.repositories;

import com.ou.pojo.CourseRate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CourseRateRepository {
    // Create operations
    CourseRate addCourseRate(CourseRate courseRate);

    // Read operations with pagination
    List<CourseRate> getCourseRates(Map<String, String> params);
    List<CourseRate> searchCourseRates(Map<String, String> filters, Map<String, String> params);

    // Return an Optional<CourseRate> to handle cases where the rate is not found
    Optional<CourseRate> getCourseRateById(Integer id);
    List<CourseRate> getCourseRatesByCourse(Integer courseId, Map<String, String> params);
    List<CourseRate> getCourseRatesByStudent(Integer studentId, Map<String, String> params);

    // Calculate average rate for a course
    double calculateAverageRate(Integer courseId);

    // Update operation
    CourseRate updateCourseRate(CourseRate courseRate);

    // Delete operation
    boolean deleteCourseRate(Integer id);

    // Count methods for pagination
    long countCourseRates();
    long countCourseRatesByCourse(Integer courseId);
    long countCourseRatesByStudent(Integer studentId);
    long countSearchResults(Map<String, String> filters);
}
