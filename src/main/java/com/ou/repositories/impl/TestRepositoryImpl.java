package com.ou.repositories.impl;

import com.ou.pojo.Test;
import com.ou.repositories.TestRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public class TestRepositoryImpl implements TestRepository {

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
