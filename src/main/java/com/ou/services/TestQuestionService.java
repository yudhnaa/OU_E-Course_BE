package com.ou.services;

public interface TestQuestionService {
    boolean addQuestionToTest(Integer testId, Integer questionId);
    boolean removeQuestionFromTest(Integer testId, Integer questionId);
}
