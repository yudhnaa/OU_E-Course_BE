package com.ou.services.impl;

import com.ou.pojo.TestAttempt;
import com.ou.repositories.TestAttemptRepository;
import com.ou.services.LocalizationService;
import com.ou.services.TestAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TestAttemptServiceImpl implements TestAttemptService {
    @Autowired
    private TestAttemptRepository testAttemptRepository;

    @Autowired
    private LocalizationService localizationService;


    @Override
    public List<TestAttempt> getAllTestAttemptsByTestId(Integer testId, Map<String, String> params){
        return testAttemptRepository.getAllTestAttemptsByTestId(testId, params);
    }

    @Override
    public long countTestAttemptsByTestId(Integer testId) {
        return testAttemptRepository.countTestAttemptsByTestId(testId);
    }

    @Override
    public Optional<TestAttempt> getTestAttemptById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(localizationService.getMessage("testAttempt.id.invalid", LocaleContextHolder.getLocale()));
        }
        return testAttemptRepository.getTestAttemptById(id);
    }

    @Override
    public TestAttempt updateTestAttempt(TestAttempt testAttempt) {
        if (testAttempt == null || testAttempt.getId() == null || testAttempt.getId() <= 0) {
            throw new IllegalArgumentException(localizationService.getMessage("testAttempt.id.invalid", LocaleContextHolder.getLocale()));
        }

        if (!isValidTestAttempt(testAttempt)) {
            throw new IllegalArgumentException(localizationService.getMessage("testAttempt.invalid", LocaleContextHolder.getLocale()));
        }

        return testAttemptRepository.updateTestAttempt(testAttempt);
    }

    @Override
    public boolean isValidTestAttempt(TestAttempt testAttempt) {
        if( testAttempt == null) {
            return false;
        }

        if( testAttempt.getTestId() == null || testAttempt.getTestId().getId() == null || testAttempt.getTestId().getId() <= 0) {
            return false;
        }

        if( testAttempt.getUserId() == null || testAttempt.getUserId().getId() == null || testAttempt.getUserId().getId() <= 0) {
            return false;
        }

        if( testAttempt.getStartedAt() == null || testAttempt.getStartedAt().isAfter(java.time.LocalDateTime.now())) {
            return false;
        }

        if(testAttempt.getTotalScore() == null || testAttempt.getTotalScore().compareTo(BigDecimal.ZERO) < 0) {
            return false;
        }

        return true;
    }

    @Override
    public TestAttempt addTestAttempt(TestAttempt testAttempt) {
        if (!isValidTestAttempt(testAttempt)) {
            throw new IllegalArgumentException(localizationService.getMessage("testAttempt.invalid", LocaleContextHolder.getLocale()));
        }

        return testAttemptRepository.addTestAttempt(testAttempt);
    }

    @Override
    public boolean deleteTestAttemptById(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(localizationService.getMessage("testAttempt.id.invalid", LocaleContextHolder.getLocale()));
        }
        return testAttemptRepository.deleteTestAttemptById(id);
    }
}
