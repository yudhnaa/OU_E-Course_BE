package com.ou.repositories;

import com.ou.pojo.Lesson;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LessonRepository {
    // Create operations
    Lesson addLesson(Lesson lesson);

    // Read operations with pagination
    List<Lesson> getLessons(Map<String, String> params);
    List<Lesson> searchLessons(Map<String, String> filters, Map<String, String> params);

    // Return an Optional<Lesson> to handle cases where the lesson is not found
    Optional<Lesson> getLessonById(Integer id);
    List<Lesson> getLessonsByName(String name, Map<String, String> params);
    List<Lesson> getLessonsByCourse(Integer courseId, Map<String, String> params);
    List<Lesson> getLessonsByType(Integer typeId, Map<String, String> params);
    List<Lesson> getLessonsByUploadUser(Integer userId, Map<String, String> params);

    // Update operation
    Lesson updateLesson(Lesson lesson);

    // Delete operation
    boolean deleteLesson(Integer id);

    // Count methods for pagination
    long countLessons(String locale);
    long countLessonsByCourse(Integer courseId, Map<String, String> params);
    long countLessonsByType(Integer typeId);
    long countLessonsByUploadUser(Integer userId);
    long countSearchResults(Map<String, String> filters);
}
