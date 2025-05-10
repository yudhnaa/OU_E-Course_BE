package com.ou.services;

import com.ou.pojo.Lesson;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LessonService {
    // Create operations
    Lesson createLesson(Lesson lesson) throws Exception;

    // Read operations with pagination
    List<Lesson> getLessons(Map<String, String> params);
    List<Lesson> searchLessons(Map<String, String> filters, Map<String, String> params);

    // Individual retrieval operations
    Optional<Lesson> getLessonById(Integer id);
    List<Lesson> getLessonsByName(String name, Map<String, String> params);
    List<Lesson> getLessonsByCourse(Integer courseId, Map<String, String> params);
    List<Lesson> getLessonsByType(Integer typeId, Map<String, String> params);
    List<Lesson> getLessonsByUploadUser(Integer userId, Map<String, String> params);

    // Update operation
    Lesson updateLesson(Lesson lesson) throws Exception;

    // Delete operation
    boolean deleteLesson(Integer id) throws Exception;

    // Count methods for pagination
    long countLessons(String locale);
    long countLessonsByCourse(Integer courseId);
    long countLessonsByType(Integer typeId);
    long countLessonsByUploadUser(Integer userId);
    long countSearchResults(Map<String, String> filters);
}
