package com.ou.repositories;

import com.ou.pojo.Test;
import com.ou.pojo.TestAttemptAnswer;

import java.util.List;
import java.util.Map;

public interface TestAttemptAnswerRepository {
    List<TestAttemptAnswer> getTestAttemptAnswersByTestAttemptId(Integer testAttemptId, Map<String, String> params);
    TestAttemptAnswer updateTestAttemptAnswer(TestAttemptAnswer testAttemptAnswer);
}
