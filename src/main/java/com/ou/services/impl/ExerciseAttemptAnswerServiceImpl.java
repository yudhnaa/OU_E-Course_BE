package com.ou.services.impl;

import com.ou.pojo.ExerciseAttempt;
import com.ou.pojo.ExerciseAttemptAnswer;
import com.ou.repositories.ExerciseAttemptAnswerRepository;
import com.ou.services.ExerciseAttemptAnswerService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ExerciseAttemptAnswerServiceImpl implements ExerciseAttemptAnswerService {
    @Autowired
    private ExerciseAttemptAnswerRepository exerciseAttemptAnswerRepository;

    @Autowired
    private LocalizationService localizationService;

    @Override
    public ExerciseAttemptAnswer addExerciseAttemptAnswer(ExerciseAttemptAnswer exerciseAttemptAnswer) {
        if (exerciseAttemptAnswer == null || exerciseAttemptAnswer.getAttemptId() == null || exerciseAttemptAnswer.getAttemptId().getId() <= 0) {
            throw new IllegalArgumentException("Invalid exercise attempt answer");
        }

        try {
            if (!isValidExerciseAttemptAnswer(exerciseAttemptAnswer)) {
                throw new IllegalArgumentException(localizationService.getMessage("exerciseAttemptAnswer.invalid", LocaleContextHolder.getLocale()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Validation failed: " + e.getMessage());
        }

        return exerciseAttemptAnswerRepository.addExerciseAttemptAnswer(exerciseAttemptAnswer);
    }

    @Override
    public List<ExerciseAttemptAnswer> getExerciseAttemptAnswers(Map<String, String> params) {
        return List.of();
    }

    @Override
    public List<ExerciseAttemptAnswer> searchExerciseAttemptAnswers(Map<String, String> filters, Map<String, String> params) {
        return List.of();
    }

    @Override
    public Optional<ExerciseAttemptAnswer> getExerciseAttemptAnswerById(Integer id) {
        return Optional.empty();
    }

    @Override
    public List<ExerciseAttemptAnswer> getExerciseAttemptAnswersByAttemptId(Integer attemptId, Map<String, String> params) {
        if (attemptId == null || attemptId <= 0) {
            throw new IllegalArgumentException("Invalid exercise attempt ID");
        }
        return exerciseAttemptAnswerRepository.getExerciseAttemptAnswersByAttemptId(attemptId, params);
    }

    @Override
    public List<ExerciseAttemptAnswer> getExerciseAttemptAnswersByUserId(Integer userId, Map<String, String> params) {
        return List.of();
    }

    @Override
    public ExerciseAttemptAnswer updateExerciseAttemptAnswer(ExerciseAttemptAnswer exerciseAttemptAnswer) {
        if (exerciseAttemptAnswer == null || exerciseAttemptAnswer.getId() == null || exerciseAttemptAnswer.getId() <= 0) {
            throw new IllegalArgumentException("Invalid exercise attempt answer");
        }

        try {
            if (!isValidExerciseAttemptAnswer(exerciseAttemptAnswer)) {
                throw new IllegalArgumentException(localizationService.getMessage("exerciseAttemptAnswer.invalid", LocaleContextHolder.getLocale()));
            }
        } catch (Exception e) {
            throw new RuntimeException("Validation failed: " + e.getMessage());
        }

        return exerciseAttemptAnswerRepository.updateExerciseAttemptAnswer(exerciseAttemptAnswer);
    }

    @Override
    public boolean deleteExerciseAttemptAnswer(Integer id) {
        return false;
    }

    @Override
    public long countExerciseAttemptAnswers() {
        return 0;
    }

    @Override
    public long countExerciseAttemptAnswersByAttemptId(Integer attemptId) {
        return 0;
    }

    @Override
    public long countExerciseAttemptAnswersByUserId(Integer userId) {
        return 0;
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        return 0;
    }

    @Override
    public boolean isValidExerciseAttemptAnswer(ExerciseAttemptAnswer exerciseAttemptAnswer) throws Exception {
        if (exerciseAttemptAnswer == null) {
            return false;
        }
        if (exerciseAttemptAnswer.getAttemptId() == null || exerciseAttemptAnswer.getAttemptId().getId() == null) {
            return false;
        }
        if(exerciseAttemptAnswer.getAnswerText().isEmpty()){
            return false;
        }
        if(exerciseAttemptAnswer.getQuestionId() == null || exerciseAttemptAnswer.getQuestionId().getId() == null) {
            return false;
        }
        return true;
    }
}
