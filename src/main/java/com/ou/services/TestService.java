package com.ou.services;

import com.ou.dto.TestDto;
import com.ou.pojo.Test;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TestService {
    List<Test> getAllTests(Map<String, String> params);
    Optional<Test> getTestById(Integer id);
    Test addTest(Test test);
    Test updateTest(Test test);
    boolean deleteTest(Integer id);
    List<Test> getTestsByCourse(Integer courseId,Map<String, String> params);
    List<Test> searchTestsByName(String name);
    List<Test> getTestsByCreatedDateRange(Date startDate, Date endDate);
    long countTestsInCourse(Integer courseId);
}
