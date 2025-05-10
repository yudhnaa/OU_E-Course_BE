package com.ou.repositories;

import com.ou.pojo.ExerciseScoreStatus;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExerciseScoreStatusRepository {
    // Create operations
    ExerciseScoreStatus addExerciseScoreStatus(ExerciseScoreStatus status);

    // Read operations with pagination
    List<ExerciseScoreStatus> getExerciseScoreStatuses(Map<String, String> params);
    List<ExerciseScoreStatus> searchExerciseScoreStatuses(Map<String, String> filters, Map<String, String> params);

    // Return an Optional<ExerciseScoreStatus> to handle cases where the status is not found
    Optional<ExerciseScoreStatus> getExerciseScoreStatusById(Integer id);
    Optional<ExerciseScoreStatus> getExerciseScoreStatusByName(String name);

    // Update operation
    ExerciseScoreStatus updateExerciseScoreStatus(ExerciseScoreStatus status);

    // Delete operation
    boolean deleteExerciseScoreStatus(Integer id);

    // Count methods for pagination
    long countExerciseScoreStatuses();
    long countSearchResults(Map<String, String> filters);
}
