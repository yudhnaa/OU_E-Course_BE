package com.ou.services;

import com.ou.pojo.Test;
import com.ou.pojo.TestAttempt;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TestAttemptService {
    List<TestAttempt> getAllTestAttemptsByTestId(Integer testId, Map<String, String> params);
    long countTestAttemptsByTestId(Integer testId);
    Optional<TestAttempt> getTestAttemptById(Integer id);
    TestAttempt updateTestAttempt(TestAttempt testAttempt);
    boolean isValidTestAttempt(TestAttempt testAttempt);
    TestAttempt addTestAttempt(TestAttempt testAttempt);
    boolean deleteTestAttemptById(Integer id);
}
