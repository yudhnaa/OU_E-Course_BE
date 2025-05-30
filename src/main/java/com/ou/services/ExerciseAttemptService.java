package com.ou.services;

import com.ou.pojo.ExerciseAttempt;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExerciseAttemptService {
    // Create operations
    ExerciseAttempt addExerciseAttempt(ExerciseAttempt exerciseAttempt);

    // Read operations with pagination
    List<ExerciseAttempt> getExerciseAttempts(Map<String, String> params);
    List<ExerciseAttempt> searchExerciseAttempts(Map<String, String> filters, Map<String, String> params);
    List<ExerciseAttempt> getExerciseAttemptsByStudentId(Integer studentId, Map<String, String> params);
    Optional<ExerciseAttempt> getExerciseAttemptById(Integer id);
    List<ExerciseAttempt> getExerciseAttemptsByExerciseId(Integer exerciseId, Map<String, String> params);
    List<ExerciseAttempt> getExerciseAttemptsByScoreByUserId(Integer userId, Map<String, String> params);
    List<ExerciseAttempt> getExerciseAttemptsByStatusId(Integer statusId, Map<String, String> params);

    // Update operation
    ExerciseAttempt updateExerciseAttempt(ExerciseAttempt exerciseAttempt);

    // Delete operation
    boolean deleteExerciseAttempt(Integer id);

    // Count methods for pagination
    long countExerciseAttempts();
    long countExerciseAttemptsByExerciseId(Integer exerciseId);
    long countExerciseAttemptsByScoreByUserId(Integer userId);
    long countExerciseAttemptsByStatusId(Integer statusId);
    long countSearchResults(Map<String, String> filters);
}
