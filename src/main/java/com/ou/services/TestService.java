package com.ou.services;

import com.ou.pojo.Test;

import java.util.Date;
import java.util.List;

public interface TestService {
    List<Test> getAllTests();
    Test getTestById(Integer id);
    Test addTest(Test test);
    Test updateTest(Test test);
    boolean deleteTest(Integer id);
    List<Test> getTestsByCourse(Integer courseId);
    List<Test> searchTestsByName(String name);
    List<Test> getTestsByCreatedDateRange(Date startDate, Date endDate);
}
