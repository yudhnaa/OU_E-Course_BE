package com.ou.services.impl;

import com.ou.pojo.ExerciseQuestion;
import com.ou.repositories.ExerciseQuestionRepository;
import com.ou.services.ExerciseQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class ExerciseQuestionServiceImpl implements ExerciseQuestionService {

    @Autowired
    private ExerciseQuestionRepository exerciseQuestionRepository;

    @Override
    public ExerciseQuestion addExerciseQuestion(ExerciseQuestion exerciseQuestion) throws Exception {
        // Validate exercise question
        validateExerciseQuestion(exerciseQuestion);
        
        // Check if exercise ID is valid
        if (exerciseQuestion.getExerciseId() == null || exerciseQuestion.getExerciseId().getId() == null) {
            throw new Exception("Exercise ID is required for creating an exercise question");
        }
        
        return exerciseQuestionRepository.addExerciseQuestion(exerciseQuestion);
    }

    @Override
    public List<ExerciseQuestion> getExerciseQuestions(Map<String, String> params) {
        validatePaginationParams(params);
        return exerciseQuestionRepository.getExerciseQuestions(params);
    }

    @Override
    public List<ExerciseQuestion> searchExerciseQuestions(Map<String, String> filters, Map<String, String> params) {
        validatePaginationParams(params);
        validateSearchFilters(filters);
        return exerciseQuestionRepository.searchExerciseQuestions(filters, params);
    }

    @Override
    public Optional<ExerciseQuestion> getExerciseQuestionById(Integer id) {
        if (id == null || id <= 0) {
            return Optional.empty();
        }
        return exerciseQuestionRepository.getExerciseQuestionById(id);
    }

    @Override
    public List<ExerciseQuestion> getExerciseQuestionsByExerciseId(Integer exerciseId, Map<String, String> params) {
        if (exerciseId == null || exerciseId <= 0) {
            throw new IllegalArgumentException("Invalid exercise ID");
        }
        validatePaginationParams(params);
        return exerciseQuestionRepository.getExerciseQuestionsByExerciseId(exerciseId, params);
    }

    @Override
    public ExerciseQuestion updateExerciseQuestion(ExerciseQuestion exerciseQuestion) throws Exception {
        // Validate exercise question
        validateExerciseQuestion(exerciseQuestion);
        
        // Check if question exists
        if (exerciseQuestion.getId() == null) {
            throw new Exception("Exercise question ID is required for updating");
        }
        
        Optional<ExerciseQuestion> existingQuestion = exerciseQuestionRepository.getExerciseQuestionById(exerciseQuestion.getId());
        if (!existingQuestion.isPresent()) {
            throw new Exception("Exercise question with ID " + exerciseQuestion.getId() + " not found");
        }
        
        // Check if exercise ID is valid
        if (exerciseQuestion.getExerciseId() == null || exerciseQuestion.getExerciseId().getId() == null) {
            throw new Exception("Exercise ID is required for an exercise question");
        }
        
        return exerciseQuestionRepository.updateExerciseQuestion(exerciseQuestion);
    }

    @Override
    public boolean deleteExerciseQuestion(Integer id) throws Exception {
        if (id == null || id <= 0) {
            throw new Exception("Invalid exercise question ID");
        }
        
        // Check if the exercise question exists
        Optional<ExerciseQuestion> existingQuestion = exerciseQuestionRepository.getExerciseQuestionById(id);
        if (!existingQuestion.isPresent()) {
            throw new Exception("Exercise question with ID " + id + " not found");
        }
        
        return exerciseQuestionRepository.deleteExerciseQuestion(id);
    }

    @Override
    public long countExerciseQuestions() {
        return exerciseQuestionRepository.countExerciseQuestions();
    }

    @Override
    public long countExerciseQuestionsByExerciseId(Integer exerciseId) {
        if (exerciseId == null || exerciseId <= 0) {
            throw new IllegalArgumentException("Invalid exercise ID");
        }
        return exerciseQuestionRepository.countExerciseQuestionsByExerciseId(exerciseId);
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        validateSearchFilters(filters);
        return exerciseQuestionRepository.countSearchResults(filters);
    }
    
    // Helper validation methods
    
    private void validateExerciseQuestion(ExerciseQuestion exerciseQuestion) throws Exception {
        if (exerciseQuestion == null) {
            throw new Exception("Exercise question cannot be null");
        }
    }
    
    private void validatePaginationParams(Map<String, String> params) {
        if (params != null) {
            // Validate page number
            if (params.containsKey("page")) {
                try {
                    int page = Integer.parseInt(params.get("page"));
                    if (page < 1) {
                        throw new IllegalArgumentException("Page number must be greater than or equal to 1");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid page number format");
                }
            }
            
            // Validate page size
            if (params.containsKey("pageSize")) {
                try {
                    int pageSize = Integer.parseInt(params.get("pageSize"));
                    if (pageSize < 1) {
                        throw new IllegalArgumentException("Page size must be greater than or equal to 1");
                    }
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid page size format");
                }
            }
        }
    }
    
    private void validateSearchFilters(Map<String, String> filters) {
        if (filters != null && filters.containsKey("exerciseId")) {
            try {
                int exerciseId = Integer.parseInt(filters.get("exerciseId"));
                if (exerciseId <= 0) {
                    throw new IllegalArgumentException("Exercise ID must be greater than 0");
                }
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Invalid exercise ID format");
            }
        }
        // Add more filter validations as needed
    }
}
