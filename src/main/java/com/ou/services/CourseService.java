package com.ou.services;

import com.ou.pojo.Course;
import com.ou.pojo.User;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public interface CourseService {
    // Create operations with validation
    Course addCourse(Course course) throws IOException;
    
    // Read operations
    List<Course> getCourses(Map<String, String> params);

    List<Course> searchCourses(Map<String, String> filters, Map<String, String> params);
    Optional<Course> getCourseById(Integer id);
    Optional<Course> getCourseByName(String name);
    List<Course> getCoursesByCategory(Integer categoryId, Map<String, String> params);
    List<Course> getCoursesCreatedByUser(Integer userId, Map<String, String> params);
    Course getCourseByIdWithPermissionCheck(int courseId, User user);
    List<Course> getCoursesByStudentId(Integer studentId, Map<String, String> filters);
    List<Object[]> getCoursesWithProgressByStudentId(Integer studentId, Map<String, String> params);

    // Update operation with validation
    Course updateCourse(Course course) throws IOException;
    
    // Delete operation
    boolean deleteCourse(Integer id);
    
    // Count methods for pagination
    long countCourses();
    long countCoursesByCategory(Integer categoryId);
    long countCoursesCreatedByUser(Integer userId);
    long countSearchResults(Map<String, String> filters);
    long countCoursesByStudentId(Integer studentId, Map<String, String> filters);
    
    // Validation methods
    boolean isValidCourse(Course course);
    boolean isNameUnique(String name, Integer excludeId);
    boolean hasValidDates(Course course);

    // calculate courses price
    BigDecimal calculateTotalPrice(Set<Integer> courseIds);

}
