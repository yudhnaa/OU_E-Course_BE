package com.ou.repositories;

import com.ou.pojo.TestAttempt;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TestAttemptRepository {
    List<TestAttempt> getAllTestAttemptsByTestId(Integer testId, Map<String, String> params);
    List<TestAttempt> getTestAttemptsByStudentId(Integer studentId, Map<String, String> params);
    long countTestAttemptsByTestId(Integer testId);
    Optional<TestAttempt> getTestAttemptById(Integer id);
    TestAttempt addTestAttempt(TestAttempt testAttempt);
    boolean deleteTestAttemptById(Integer id);
    TestAttempt updateTestAttempt(TestAttempt testAttempt);
}
