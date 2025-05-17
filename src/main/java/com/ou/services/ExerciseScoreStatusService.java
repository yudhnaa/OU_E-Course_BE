package com.ou.services;

import com.ou.pojo.ExerciseScoreStatus;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExerciseScoreStatusService {
    // Create operation with validation
    ExerciseScoreStatus createExerciseScoreStatus(ExerciseScoreStatus status) throws Exception;
    
    // Read operations
    List<ExerciseScoreStatus> getExerciseScoreStatuses(Map<String, String> params);
    List<ExerciseScoreStatus> searchExerciseScoreStatuses(Map<String, String> filters, Map<String, String> params);
    Optional<ExerciseScoreStatus> getExerciseScoreStatusById(Integer id);
    Optional<ExerciseScoreStatus> getExerciseScoreStatusByName(String name);
    
    // Update operation with validation
    ExerciseScoreStatus updateExerciseScoreStatus(ExerciseScoreStatus status) throws Exception;
    
    // Delete operation with validation
    boolean deleteExerciseScoreStatus(Integer id) throws Exception;
    
    // Count methods for pagination
    long countExerciseScoreStatuses();
    long countSearchResults(Map<String, String> filters);
}
