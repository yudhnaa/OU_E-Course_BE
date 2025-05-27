package com.ou.repositories;

public interface TestQuestionRepository {
    boolean addQuestionToTest(Integer testId, Integer questionId);
    boolean removeQuestionFromTest(Integer testId, Integer questionId);
}
