package com.ou.repositories;

import com.ou.pojo.Course;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CourseRepository {
    // Create operations
    Course addCourse(Course course);

    // Read operations with pagination
    List<Course> getCourses(Map<String, String> params);
    List<Course> searchCourses(Map<String, String> filters, Map<String, String> params);

    // Return an Optional<Course> to handle cases where the course is not found
    Optional<Course> getCourseById(Integer id);
    Optional<Course> getCourseByName(String name);
    List<Course> getCoursesByCategory(Integer categoryId, Map<String, String> params);
    List<Course> getCoursesCreatedByUser(Integer userId, Map<String, String> params);
    
    // Update operation
    Course updateCourse(Course course);

    // Delete operation
    boolean deleteCourse(Integer id);

    // Count methods for pagination
    long countCourses();
    long countCoursesByCategory(Integer categoryId);
    long countCoursesCreatedByUser(Integer userId);
    long countSearchResults(Map<String, String> filters);


}
