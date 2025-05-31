package com.ou.services;

import com.ou.pojo.CourseLecturer;
import com.ou.pojo.Lecturer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CourseLecturerService {
    // Create operations
    CourseLecturer addCourseLecturer(CourseLecturer courseLecturer) throws Exception;
    Boolean updateCourseLecturer(List<CourseLecturer> courseLecturer) throws Exception;

    // Read operations with pagination
    List<CourseLecturer> getCourseLecturers(Map<String, String> params);
    List<Lecturer> getLecturersByCourseId(Integer courseId, Map<String, String> params);
    List<CourseLecturer> searchCourseLecturers(Map<String, String> filters, Map<String, String> params);

    // Get a specific course-lecturer assignment
    Optional<CourseLecturer> getCourseLecturerById(Integer id);
    List<CourseLecturer> getCourseLecturersByCourse(Integer courseId, Map<String, String> params);
    List<CourseLecturer> getCourseLecturersByLecturer(Integer lecturerId, Map<String, String> params);

    // Update operation
    CourseLecturer updateCourseLecturer(CourseLecturer courseLecturer) throws Exception;

    // Delete operation
    boolean deleteCourseLecturer(Integer id) throws Exception;

    // Count methods for pagination
    long countCourseLecturers();
    long countCourseLecturersByCourse(Integer courseId);
    long countCourseLecturersByLecturer(Integer lecturerId);
    long countSearchResults(Map<String, String> filters);
    
    // Business logic validation
    boolean isLecturerAssignedToCourse(Integer lecturerId, Integer courseId);
    boolean validateCourseLecturerAssignment(CourseLecturer courseLecturer) throws Exception;
}
