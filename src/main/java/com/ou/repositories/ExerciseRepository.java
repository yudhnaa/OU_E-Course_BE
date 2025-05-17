package com.ou.repositories;

import com.ou.pojo.Exercise;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExerciseRepository {
    // Create operations
    Exercise addExercise(Exercise exercise);

    // Read operations with pagination
    List<Exercise> getExercises(Map<String, String> params);
    List<Exercise> searchExercises(Map<String, String> filters, Map<String, String> params);

    // Return an Optional<Exercise> to handle cases where the exercise is not found to avoid null checks
    Optional<Exercise> getExerciseById(Integer id);
    List<Exercise> getExercisesByCourse(Integer courseId, Map<String, String> params);
    List<Exercise> getExercisesByLesson(Integer lessonId, Map<String, String> params);
    List<Exercise> getExercisesByCreator(Integer userId, Map<String, String> params);

    // Update operation
    Exercise updateExercise(Exercise exercise);

    // Delete operation
    boolean deleteExercise(Integer id);

    // Count methods for pagination
    long countExercises();
    long countExercisesByCourse(Integer courseId);
    long countExercisesByLesson(Integer lessonId);
    long countExercisesByCreator(Integer userId);
    long countSearchResults(Map<String, String> filters);
}
