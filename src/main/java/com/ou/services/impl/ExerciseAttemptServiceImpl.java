package com.ou.services.impl;

import com.ou.pojo.ExerciseAttempt;
import com.ou.repositories.ExerciseAttemptRepository;
import com.ou.services.ExerciseAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ExerciseAttemptServiceImpl implements ExerciseAttemptService {

    @Autowired
    private ExerciseAttemptRepository exerciseAttemptRepository;

    @Override
    public ExerciseAttempt addExerciseAttempt(ExerciseAttempt exerciseAttempt) {
        // Business logic validation
        validateExerciseAttempt(exerciseAttempt);
        
        // Set started time if not provided
        if (exerciseAttempt.getStartedAt() == null) {
            exerciseAttempt.setStartedAt(new Date());
        }
        
        return exerciseAttemptRepository.addExerciseAttempt(exerciseAttempt);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttempt> getExerciseAttempts(Map<String, String> params) {
        return exerciseAttemptRepository.getExerciseAttempts(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttempt> searchExerciseAttempts(Map<String, String> filters, Map<String, String> params) {
        // Validate search filters if needed
        return exerciseAttemptRepository.searchExerciseAttempts(filters, params);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExerciseAttempt> getExerciseAttemptById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid exercise attempt ID");
        }
        return exerciseAttemptRepository.getExerciseAttemptById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttempt> getExerciseAttemptsByExerciseId(Integer exerciseId, Map<String, String> params) {
        if (exerciseId == null || exerciseId <= 0) {
            throw new IllegalArgumentException("Invalid exercise ID");
        }
        return exerciseAttemptRepository.getExerciseAttemptsByExerciseId(exerciseId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttempt> getExerciseAttemptsByScoreByUserId(Integer userId, Map<String, String> params) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        return exerciseAttemptRepository.getExerciseAttemptsByScoreByUserId(userId, params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseAttempt> getExerciseAttemptsByStatusId(Integer statusId, Map<String, String> params) {
        if (statusId == null || statusId <= 0) {
            throw new IllegalArgumentException("Invalid status ID");
        }
        return exerciseAttemptRepository.getExerciseAttemptsByStatusId(statusId, params);
    }

    @Override
    public ExerciseAttempt updateExerciseAttempt(ExerciseAttempt exerciseAttempt) {
        // Check if exists
        Optional<ExerciseAttempt> existingAttemptOpt = exerciseAttemptRepository.getExerciseAttemptById(exerciseAttempt.getId());
        if (existingAttemptOpt.isEmpty()) {
            throw new IllegalArgumentException("Exercise attempt with ID " + exerciseAttempt.getId() + " does not exist");
        }
        
        // Business logic validation
        validateExerciseAttempt(exerciseAttempt);
        
        // Handle submission logic if applicable
        ExerciseAttempt existingAttempt = existingAttemptOpt.get();
        if (existingAttempt.getSubmittedAt() == null && exerciseAttempt.getSubmittedAt() != null) {
            // This is a submission - perform submission-specific validations
            validateSubmission(exerciseAttempt);
        }
        
        return exerciseAttemptRepository.updateExerciseAttempt(exerciseAttempt);
    }

    @Override
    public boolean deleteExerciseAttempt(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid exercise attempt ID");
        }
        
        // Check if exists before deleting
        if (exerciseAttemptRepository.getExerciseAttemptById(id).isEmpty()) {
            throw new IllegalArgumentException("Exercise attempt with ID " + id + " does not exist");
        }
        
        return exerciseAttemptRepository.deleteExerciseAttempt(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countExerciseAttempts() {
        return exerciseAttemptRepository.countExerciseAttempts();
    }

    @Override
    @Transactional(readOnly = true)
    public long countExerciseAttemptsByExerciseId(Integer exerciseId) {
        if (exerciseId == null || exerciseId <= 0) {
            throw new IllegalArgumentException("Invalid exercise ID");
        }
        return exerciseAttemptRepository.countExerciseAttemptsByExerciseId(exerciseId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countExerciseAttemptsByScoreByUserId(Integer userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }
        return exerciseAttemptRepository.countExerciseAttemptsByScoreByUserId(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countExerciseAttemptsByStatusId(Integer statusId) {
        if (statusId == null || statusId <= 0) {
            throw new IllegalArgumentException("Invalid status ID");
        }
        return exerciseAttemptRepository.countExerciseAttemptsByStatusId(statusId);
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        return exerciseAttemptRepository.countSearchResults(filters);
    }
    
    // Private validation methods
    private void validateExerciseAttempt(ExerciseAttempt exerciseAttempt) {
        if (exerciseAttempt == null) {
            throw new IllegalArgumentException("Exercise attempt cannot be null");
        }
        
        if (exerciseAttempt.getResponse() == null || exerciseAttempt.getResponse().trim().isEmpty()) {
            throw new IllegalArgumentException("Exercise attempt response cannot be empty");
        }
        
        if (exerciseAttempt.getExerciseId() == null) {
            throw new IllegalArgumentException("Exercise ID cannot be null");
        }
        
        if (exerciseAttempt.getStatusId() == null) {
            throw new IllegalArgumentException("Status ID cannot be null");
        }
        
        if (exerciseAttempt.getScoreByUserId() == null) {
            throw new IllegalArgumentException("Scorer user ID cannot be null");
        }
        
        // Validation for date consistency
        if (exerciseAttempt.getStartedAt() != null && exerciseAttempt.getSubmittedAt() != null) {
            if (exerciseAttempt.getSubmittedAt().before(exerciseAttempt.getStartedAt())) {
                throw new IllegalArgumentException("Submission date cannot be before start date");
            }
        }
        
        // Score validation if present
        if (exerciseAttempt.getTotalScore() != null) {
            if (exerciseAttempt.getTotalScore().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("Total score cannot be negative");
            }
        }
    }
    
    private void validateSubmission(ExerciseAttempt exerciseAttempt) {
        // Additional validations specific to submissions
        if (exerciseAttempt.getResponse() == null || exerciseAttempt.getResponse().trim().isEmpty()) {
            throw new IllegalArgumentException("Submission must include a response");
        }
        
        // Set submission time if not provided
        if (exerciseAttempt.getSubmittedAt() == null) {
            exerciseAttempt.setSubmittedAt(new Date());
        }
    }
}
