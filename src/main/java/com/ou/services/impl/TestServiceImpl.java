package com.ou.services.impl;
import com.ou.pojo.Test;
import com.ou.services.TestService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public List<Test> getAllTests() {
        return List.of();
    }

    @Override
    public Test getTestById(Integer id) {
        return null;
    }

    @Override
    public Test addTest(Test test) {
        return null;
    }

    @Override
    public Test updateTest(Test test) {
        return null;
    }

    @Override
    public boolean deleteTest(Integer id) {
        return false;
    }

    @Override
    public List<Test> getTestsByCourse(Integer courseId) {
        return List.of();
    }

    @Override
    public List<Test> searchTestsByName(String name) {
        return List.of();
    }

    @Override
    public List<Test> getTestsByCreatedDateRange(Date startDate, Date endDate) {
        return List.of();
    }
}
