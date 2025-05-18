package com.ou.services;

import com.ou.pojo.Exercise;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExerciseService {
    // Create operations
    Exercise createExercise(Exercise exercise) throws Exception;

    // Read operations with pagination
    List<Exercise> getExercises(Map<String, String> params);
    List<Exercise> searchExercises(Map<String, String> filters, Map<String, String> params);
    Optional<Exercise> getExerciseById(Integer id) throws Exception;
    List<Exercise> getExercisesByCourse(Integer courseId, Map<String, String> params);
    List<Exercise> getExercisesByLesson(Integer lessonId, Map<String, String> params);
    List<Exercise> getExercisesByCreator(Integer userId, Map<String, String> params);

    // Update operation
    Exercise updateExercise(Exercise exercise) throws Exception;

    // Delete operation
    boolean deleteExercise(Integer id) throws Exception;

    // Count methods for pagination
    long countExercises();
    long countExercisesByCourse(Integer courseId);
    long countExercisesByLesson(Integer lessonId);
    long countExercisesByCreator(Integer userId);
    long countSearchResults(Map<String, String> filters);
}
