package com.ou.repositories;

import com.ou.pojo.CourseStudent;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CourseStudentRepository {
    // Create operations
    CourseStudent addCourseStudent(CourseStudent courseStudent);

    // Read operations with pagination
    List<CourseStudent> getCourseStudents(Map<String, String> params);
    List<CourseStudent> searchCourseStudents(Map<String, String> filters, Map<String, String> params);

    // Get by ID
    Optional<CourseStudent> getCourseStudentById(Integer id);
    
    // Get by relations
    List<CourseStudent> getCourseStudentsByCourse(Integer courseId, Map<String, String> params);
    List<CourseStudent> getCourseStudentsByStudent(Integer studentId, Map<String, String> params);
    Optional<CourseStudent> getCourseStudentByCourseAndStudent(Integer courseId, Integer studentId);

    // Update operation
    CourseStudent updateCourseStudent(CourseStudent courseStudent);

    // Delete operation
    boolean deleteCourseStudent(Integer id);

    // Count methods for pagination
    long countCourseStudents();
    long countCourseStudentsByCourse(Integer courseId);
    long countCourseStudentsByStudent(Integer studentId);
    long countSearchResults(Map<String, String> filters);
}
