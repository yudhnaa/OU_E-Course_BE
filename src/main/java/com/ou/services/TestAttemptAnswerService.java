package com.ou.services;

import com.ou.pojo.TestAttemptAnswer;

import java.util.List;
import java.util.Map;

public interface TestAttemptAnswerService {
    List<TestAttemptAnswer> getTestAttemptAnswersByTestAttemptId(Integer testAttemptId, Map<String, String> params);
    TestAttemptAnswer updateTestAttemptAnswer(TestAttemptAnswer testAttemptAnswer);
    boolean isValidTestAttemptAnswer(TestAttemptAnswer testAttemptAnswer);
}
