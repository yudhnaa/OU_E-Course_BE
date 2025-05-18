package com.ou.services.impl;

import com.ou.pojo.Test;
import com.ou.repositories.TestRepository;
import com.ou.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository testRepositoryImpl;



    @Override
    public List<Test> getAllTests(Map<String, String> params) {
        return testRepositoryImpl.getAllTests(params);
    }

    @Override
    public Optional<Test> getTestById(Integer id) {
        return testRepositoryImpl.getTestById(id);
    }

    @Override
    public Test addTest(Test test) {
        return testRepositoryImpl.addTest(test);
    }

    @Override
    public Test updateTest(Test test) {
        return testRepositoryImpl.updateTest(test);
    }

    @Override
    public boolean deleteTest(Integer id) {
        return testRepositoryImpl.deleteTest(id);
    }

    @Override
    public List<Test> getTestsByCourse(Integer courseId) {
        return testRepositoryImpl.getTestsByCourse(courseId);
    }

    @Override
    public List<Test> searchTestsByName(String name) {
        return testRepositoryImpl.searchTestsByName(name);
    }

    @Override
    public List<Test> getTestsByCreatedDateRange(Date startDate, Date endDate) {
        return testRepositoryImpl.getTestsByCreatedDateRange(startDate, endDate);
    }
}
