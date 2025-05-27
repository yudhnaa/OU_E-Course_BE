package com.ou.repositories;

import com.ou.pojo.CourseLecturer;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CourseLecturerRepository {
    // Create operations
    CourseLecturer addCourseLecturer(CourseLecturer courseLecturer);

    // Read operations with pagination
    List<CourseLecturer> getCourseLecturers(Map<String, String> params);
    List<CourseLecturer> searchCourseLecturers(Map<String, String> filters, Map<String, String> params);

    // Return an Optional<CourseLecturer> to handle cases where the entity is not found
    Optional<CourseLecturer> getCourseLecturerById(Integer id);
    List<CourseLecturer> getCourseLecturersByCourse(Integer courseId, Map<String, String> params);
    List<CourseLecturer> getCourseLecturersByLecturer(Integer lecturerId, Map<String, String> params);

    // Update operation
    CourseLecturer updateCourseLecturer(CourseLecturer courseLecturer);

    // Delete operation
    boolean deleteCourseLecturer(Integer id);

    // Count methods for pagination
    long countCourseLecturers();
    long countCourseLecturersByCourse(Integer courseId);
    long countCourseLecturersByLecturer(Integer lecturerId);
    long countSearchResults(Map<String, String> filters);

    //validation methods
    boolean existsByCourseIdAndLecturerId(int courseId, int lecturerId);
}
