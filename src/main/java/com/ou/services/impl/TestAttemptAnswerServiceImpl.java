package com.ou.services.impl;

import com.ou.pojo.TestAttemptAnswer;
import com.ou.repositories.TestAttemptAnswerRepository;
import com.ou.services.LocalizationService;
import com.ou.services.TestAttemptAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TestAttemptAnswerServiceImpl implements TestAttemptAnswerService {
    @Autowired
    private TestAttemptAnswerRepository testAttemptAnswerRepository;

    @Autowired
    private LocalizationService localizationService;

    @Override
    public List<TestAttemptAnswer> getTestAttemptAnswersByTestAttemptId(Integer testAttemptId, Map<String, String> params) {
        if (testAttemptId == null || testAttemptId <= 0) {
            throw new IllegalArgumentException("Invalid test attempt ID");
        }
        return testAttemptAnswerRepository.getTestAttemptAnswersByTestAttemptId(testAttemptId, params);
    }

    @Override
    public TestAttemptAnswer updateTestAttemptAnswer(TestAttemptAnswer testAttemptAnswer) {
        if(!isValidTestAttemptAnswer(testAttemptAnswer)){
            throw new IllegalArgumentException(localizationService.getMessage("testAttemptAnswer.invalid", LocaleContextHolder.getLocale()));
        }
        return testAttemptAnswerRepository.updateTestAttemptAnswer(testAttemptAnswer);
    }

    @Override
    public boolean isValidTestAttemptAnswer(TestAttemptAnswer testAttemptAnswer) {

        if (testAttemptAnswer == null) {
            return false;
        }

        if (testAttemptAnswer.getId() == null || testAttemptAnswer.getId() <= 0) {
            return false;
        }

        if (testAttemptAnswer.getAttemptId() == null || testAttemptAnswer.getAttemptId().getId() <= 0) {
            return false;
        }

        if (testAttemptAnswer.getQuestionId() == null || testAttemptAnswer.getQuestionId().getId() <= 0) {
            return false;
        }

        if (testAttemptAnswer.getAnswerText() == null || testAttemptAnswer.getAnswerText().isEmpty()) {
            return false;
        }
        return true;
    }
}
