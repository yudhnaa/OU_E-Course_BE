package com.ou.services;

import com.ou.pojo.ExerciseAttachment;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ExerciseAttachmentService {
    // Create operations
    ExerciseAttachment createExerciseAttachment(ExerciseAttachment exerciseAttachment);
    
    // Read operations
    List<ExerciseAttachment> getExerciseAttachments(Map<String, String> params);
    Optional<ExerciseAttachment> getExerciseAttachmentById(Integer id);
    List<ExerciseAttachment> getExerciseAttachmentsByExercise(Integer exerciseId, Map<String, String> params);
    List<ExerciseAttachment> getExerciseAttachmentsByAttachment(Integer attachmentId, Map<String, String> params);
    List<ExerciseAttachment> searchExerciseAttachments(Map<String, String> filters, Map<String, String> params);
    
    // Update operation
    ExerciseAttachment updateExerciseAttachment(ExerciseAttachment exerciseAttachment);
    
    // Delete operation
    boolean deleteExerciseAttachment(Integer id);
    
    // Count methods for pagination
    long countExerciseAttachments();
    long countExerciseAttachmentsByExercise(Integer exerciseId);
    long countExerciseAttachmentsByAttachment(Integer attachmentId);
    long countSearchResults(Map<String, String> filters);
}
