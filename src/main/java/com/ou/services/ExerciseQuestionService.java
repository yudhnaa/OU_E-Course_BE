package com.ou.services;

import com.ou.pojo.ExerciseQuestion;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExerciseQuestionService {
    // Create operation
    ExerciseQuestion addExerciseQuestion(ExerciseQuestion exerciseQuestion) throws Exception;
    
    // Read operations with pagination
    List<ExerciseQuestion> getExerciseQuestions(Map<String, String> params);
    List<ExerciseQuestion> searchExerciseQuestions(Map<String, String> filters, Map<String, String> params);
    
    // Return an Optional<ExerciseQuestion> to handle cases where the question is not found
    Optional<ExerciseQuestion> getExerciseQuestionById(Integer id);
    List<ExerciseQuestion> getExerciseQuestionsByExerciseId(Integer exerciseId, Map<String, String> params);
    
    // Update operation
    ExerciseQuestion updateExerciseQuestion(ExerciseQuestion exerciseQuestion) throws Exception;
    
    // Delete operation
    boolean deleteExerciseQuestion(Integer id) throws Exception;
    
    // Count methods for pagination
    long countExerciseQuestions();
    long countExerciseQuestionsByExerciseId(Integer exerciseId);
    long countSearchResults(Map<String, String> filters);
}
