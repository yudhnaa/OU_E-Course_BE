package com.ou.services;

import com.ou.pojo.ExerciseAttempt;
import com.ou.pojo.ExerciseAttemptAnswer;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExerciseAttemptAnswerService {
    // Create operations
    ExerciseAttemptAnswer addExerciseAttemptAnswer(ExerciseAttemptAnswer exerciseAttemptAnswer);

    // Read operations with pagination
    List<ExerciseAttemptAnswer> getExerciseAttemptAnswers(Map<String, String> params);
    List<ExerciseAttemptAnswer> searchExerciseAttemptAnswers(Map<String, String> filters, Map<String, String> params);

    // Return an Optional<ExerciseAttemptAnswer> to handle cases where the answer is not found to avoid null checks
    Optional<ExerciseAttemptAnswer> getExerciseAttemptAnswerById(Integer id);
    List<ExerciseAttemptAnswer> getExerciseAttemptAnswersByAttemptId(Integer attemptId, Map<String, String> params);
    List<ExerciseAttemptAnswer> getExerciseAttemptAnswersByUserId(Integer userId, Map<String, String> params);

    // Update operation
    ExerciseAttemptAnswer updateExerciseAttemptAnswer(ExerciseAttemptAnswer exerciseAttemptAnswer);

    // Delete operation
    boolean deleteExerciseAttemptAnswer(Integer id);

    // Count methods for pagination
    long countExerciseAttemptAnswers();
    long countExerciseAttemptAnswersByAttemptId(Integer attemptId);
    long countExerciseAttemptAnswersByUserId(Integer userId);
    long countSearchResults(Map<String, String> filters);
    boolean isValidExerciseAttemptAnswer(ExerciseAttemptAnswer exerciseAttemptAnswer) throws Exception;
}
