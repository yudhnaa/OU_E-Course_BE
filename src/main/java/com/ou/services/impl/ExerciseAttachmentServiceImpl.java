package com.ou.services.impl;

import com.ou.pojo.ExerciseAttachment;
import com.ou.repositories.ExerciseAttachmentRepository;
import com.ou.services.ExerciseAttachmentService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ExerciseAttachmentServiceImpl implements ExerciseAttachmentService {

    @Autowired
    private ExerciseAttachmentRepository exerciseAttachmentRepository;
    @Autowired
    private LocalizationService localizationService;

    @Override
    public ExerciseAttachment createExerciseAttachment(ExerciseAttachment exerciseAttachment) {
        // Validate input
        if (exerciseAttachment == null)
            throw new IllegalArgumentException("Exercise attachment cannot be null");
        
        if (exerciseAttachment.getExerciseId() == null)
            throw new IllegalArgumentException("Exercise ID is required");
        
        if (exerciseAttachment.getAttachmentId() == null)
            throw new IllegalArgumentException("Attachment ID is required");
        
        return exerciseAttachmentRepository.addExerciseAttachment(exerciseAttachment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttachment> getExerciseAttachments(Map<String, String> params) {
        // Validate pagination parameters if needed
        validatePaginationParams(params);
        
        return exerciseAttachmentRepository.getExerciseAttachments(params);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExerciseAttachment> getExerciseAttachmentById(Integer id) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("Invalid exercise attachment ID");
            
        return exerciseAttachmentRepository.getExerciseAttachmentById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttachment> getExerciseAttachmentsByExercise(Integer exerciseId, Map<String, String> params) {
        if (exerciseId == null || exerciseId <= 0)
            throw new IllegalArgumentException("Invalid exercise ID");
            
        validatePaginationParams(params);
        
        return exerciseAttachmentRepository.getExerciseAttachmentsByExercise(exerciseId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttachment> getExerciseAttachmentsByAttachment(Integer attachmentId, Map<String, String> params) {
        if (attachmentId == null || attachmentId <= 0)
            throw new IllegalArgumentException("Invalid attachment ID");
            
        validatePaginationParams(params);
        
        return exerciseAttachmentRepository.getExerciseAttachmentsByAttachment(attachmentId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttachment> searchExerciseAttachments(Map<String, String> filters, Map<String, String> params) {
        validatePaginationParams(params);
        // Additional filter validation can be added here if needed
        
        return exerciseAttachmentRepository.searchExerciseAttachments(filters, params);
    }

    @Override
    public ExerciseAttachment updateExerciseAttachment(ExerciseAttachment exerciseAttachment) {
        // Validate input
        if (exerciseAttachment == null)
            throw new IllegalArgumentException("Exercise attachment cannot be null");
        
        if (exerciseAttachment.getId() == null || exerciseAttachment.getId() <= 0)
            throw new IllegalArgumentException("Invalid exercise attachment ID");
            
        if (exerciseAttachment.getExerciseId() == null)
            throw new IllegalArgumentException("Exercise ID is required");
            
        if (exerciseAttachment.getAttachmentId() == null)
            throw new IllegalArgumentException("Attachment ID is required");
        
        // Check if the exercise attachment exists
        Optional<ExerciseAttachment> existingAttachment = exerciseAttachmentRepository.getExerciseAttachmentById(exerciseAttachment.getId());
        if (!existingAttachment.isPresent()) {
            throw new IllegalArgumentException("Exercise attachment with ID " + exerciseAttachment.getId() + " not found");
        }
        
        return exerciseAttachmentRepository.updateExerciseAttachment(exerciseAttachment);
    }

    @Override
    public boolean deleteExerciseAttachment(Integer id) {
        if (id == null || id <= 0)
            throw new IllegalArgumentException("Invalid exercise attachment ID");
            
        // Check if the exercise attachment exists
        Optional<ExerciseAttachment> existingAttachment = exerciseAttachmentRepository.getExerciseAttachmentById(id);
        if (!existingAttachment.isPresent()) {
            throw new IllegalArgumentException("Exercise attachment with ID " + id + " not found");
        }
        
        return exerciseAttachmentRepository.deleteExerciseAttachment(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countExerciseAttachments() {
        return exerciseAttachmentRepository.countExerciseAttachments();
    }

    @Override
    @Transactional(readOnly = true)
    public long countExerciseAttachmentsByExercise(Integer exerciseId) {
        if (exerciseId == null || exerciseId <= 0)
            throw new IllegalArgumentException("Invalid exercise ID");
            
        return exerciseAttachmentRepository.countExerciseAttachmentsByExercise(exerciseId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countExerciseAttachmentsByAttachment(Integer attachmentId) {
        if (attachmentId == null || attachmentId <= 0)
            throw new IllegalArgumentException("Invalid attachment ID");
            
        return exerciseAttachmentRepository.countExerciseAttachmentsByAttachment(attachmentId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countExerciseAttachmentsByLesson(Integer lessonId) {
        if (lessonId == null || lessonId <= 0)
            throw new IllegalArgumentException(localizationService.getMessage("exerciseAttachment.invalid.id", LocaleContextHolder.getLocale()));

        // Check if the lesson exists
        Optional<ExerciseAttachment> existingLesson = exerciseAttachmentRepository.getExerciseAttachmentById(lessonId);
        if (!existingLesson.isPresent()) {
            throw new IllegalArgumentException(localizationService.getMessage("exerciseAttachment.notFound", LocaleContextHolder.getLocale()));
        }

        return exerciseAttachmentRepository.countExerciseAttachmentsByLesson(lessonId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        return exerciseAttachmentRepository.countSearchResults(filters);
    }
    
    // Helper method to validate pagination parameters
    private void validatePaginationParams(Map<String, String> params) {
        if (params != null) {
            if (params.containsKey("page")) {
                try {
                    int page = Integer.parseInt(params.get("page"));
                    if (page < 1) {
                        throw new IllegalArgumentException("Page number must be positive");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid page number format");
                }
            }
            
            if (params.containsKey("pageSize")) {
                try {
                    int pageSize = Integer.parseInt(params.get("pageSize"));
                    if (pageSize < 1) {
                        throw new IllegalArgumentException("Page size must be positive");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid page size format");
                }
            }
        }
    }
}
