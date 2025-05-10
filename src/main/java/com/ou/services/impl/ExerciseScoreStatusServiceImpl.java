package com.ou.services.impl;

import com.ou.pojo.ExerciseScoreStatus;
import com.ou.repositories.ExerciseScoreStatusRepository;
import com.ou.services.ExerciseScoreStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ExerciseScoreStatusServiceImpl implements ExerciseScoreStatusService {

    @Autowired
    private ExerciseScoreStatusRepository exerciseScoreStatusRepository;

    @Override
    public ExerciseScoreStatus createExerciseScoreStatus(ExerciseScoreStatus status) throws Exception {
        // Validate the status object
        validateScoreStatus(status);
        
        // Check for duplicate name
        if (exerciseScoreStatusRepository.getExerciseScoreStatusByName(status.getName()).isPresent()) {
            throw new Exception("A status with name '" + status.getName() + "' already exists.");
        }
        
        return exerciseScoreStatusRepository.addExerciseScoreStatus(status);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseScoreStatus> getExerciseScoreStatuses(Map<String, String> params) {
        return exerciseScoreStatusRepository.getExerciseScoreStatuses(params);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ExerciseScoreStatus> searchExerciseScoreStatuses(Map<String, String> filters, Map<String, String> params) {
        return exerciseScoreStatusRepository.searchExerciseScoreStatuses(filters, params);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExerciseScoreStatus> getExerciseScoreStatusById(Integer id) {
        if (id == null || id <= 0) {
            return Optional.empty();
        }
        return exerciseScoreStatusRepository.getExerciseScoreStatusById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExerciseScoreStatus> getExerciseScoreStatusByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return Optional.empty();
        }
        return exerciseScoreStatusRepository.getExerciseScoreStatusByName(name);
    }

    @Override
    public ExerciseScoreStatus updateExerciseScoreStatus(ExerciseScoreStatus status) throws Exception {
        // Check if the status exists
        Optional<ExerciseScoreStatus> existingStatus = exerciseScoreStatusRepository.getExerciseScoreStatusById(status.getId());
        if (!existingStatus.isPresent()) {
            throw new Exception("Status with ID " + status.getId() + " does not exist.");
        }
        
        // Validate the status object
        validateScoreStatus(status);
        
        // Check for duplicate name (excluding current status)
        Optional<ExerciseScoreStatus> statusWithSameName = exerciseScoreStatusRepository.getExerciseScoreStatusByName(status.getName());
        if (statusWithSameName.isPresent() && !statusWithSameName.get().getId().equals(status.getId())) {
            throw new Exception("Another status with name '" + status.getName() + "' already exists.");
        }
        
        return exerciseScoreStatusRepository.updateExerciseScoreStatus(status);
    }

    @Override
    public boolean deleteExerciseScoreStatus(Integer id) throws Exception {
        // Check if the status exists
        Optional<ExerciseScoreStatus> existingStatus = exerciseScoreStatusRepository.getExerciseScoreStatusById(id);
        if (!existingStatus.isPresent()) {
            throw new Exception("Status with ID " + id + " does not exist.");
        }
        
        // Check if the status is being referenced by any exercise attempts
        ExerciseScoreStatus status = existingStatus.get();
        if (status.getExerciseAttemptSet() != null && !status.getExerciseAttemptSet().isEmpty()) {
            throw new Exception("Cannot delete status as it is being used by " + 
                              status.getExerciseAttemptSet().size() + " exercise attempts.");
        }
        
        return exerciseScoreStatusRepository.deleteExerciseScoreStatus(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countExerciseScoreStatuses() {
        return exerciseScoreStatusRepository.countExerciseScoreStatuses();
    }

    @Override
    @Transactional(readOnly = true)
    public long countSearchResults(Map<String, String> filters) {
        return exerciseScoreStatusRepository.countSearchResults(filters);
    }
    
    // Helper method for validation
    private void validateScoreStatus(ExerciseScoreStatus status) throws Exception {
        // Check for null object
        if (status == null) {
            throw new Exception("Status cannot be null.");
        }
        
        // Validate name
        if (status.getName() == null || status.getName().trim().isEmpty()) {
            throw new Exception("Status name cannot be empty.");
        }
        
        if (status.getName().length() > 50) {
            throw new Exception("Status name cannot exceed 50 characters.");
        }
        
        // Validate description (if provided)
        if (status.getDescription() != null && status.getDescription().length() > 65535) {
            throw new Exception("Status description exceeds maximum length.");
        }
    }
}
