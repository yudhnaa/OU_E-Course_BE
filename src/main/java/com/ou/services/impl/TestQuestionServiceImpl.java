package com.ou.services.impl;

import com.ou.repositories.TestQuestionRepository;
import com.ou.services.TestQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestQuestionServiceImpl implements TestQuestionService {

    @Autowired
    private TestQuestionRepository testQuestionRepository;


    @Override
    public boolean addQuestionToTest(Integer testId, Integer questionId) {
        if (testId == null || questionId == null) {
            return false;
        }
        return testQuestionRepository.addQuestionToTest(testId, questionId);
    }

    @Override
    public boolean removeQuestionFromTest(Integer testId, Integer questionId) {
        if (testId == null || questionId == null) {
            return false;
        }
        return testQuestionRepository.removeQuestionFromTest(testId, questionId);
    }
}
