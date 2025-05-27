package com.ou.repositories;

import com.ou.pojo.Lecturer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LecturerRepository {
    // Create operations
    Lecturer addLecturer(Lecturer lecturer);

    // Read operations with pagination
    List<Lecturer> getLecturers(Map<String, String> params);

    // Return an Optional<Lecturer> to handle cases where the lecturer is not found
    Optional<Lecturer> getLecturerById(Integer id);
    Optional<Lecturer> getLecturerByUserId(Integer userId);
    List<Lecturer> getActiveLecturers(Map<String, String> params);

    // Update operation
    Lecturer updateLecturer(Lecturer lecturer);

    // Delete operation
    boolean deleteLecturer(Integer id);

    // Count methods for pagination
    long countLecturers();
    long countLecturersByCourse(Integer courseId);
    long countActiveLecturers();
    long countSearchResults(Map<String, String> filters);
}
