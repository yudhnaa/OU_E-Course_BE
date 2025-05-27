package com.ou.services.impl;

import com.ou.pojo.Test;
import com.ou.repositories.TestRepository;
import com.ou.services.LocalizationService;
import com.ou.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
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

    @Autowired
    private LocalizationService localizationService;

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
        if (!isValidTest(test)) {
            throw new IllegalArgumentException(localizationService.getMessage("test.invalid", LocaleContextHolder.getLocale()));
        }

        return testRepositoryImpl.addTest(test);
    }

    @Override
    public Test updateTest(Test test) {
        if (!isValidTest(test)) {
            throw new IllegalArgumentException(localizationService.getMessage("test.invalid", LocaleContextHolder.getLocale()));
        }

        if(test.getId() == null || test.getId() <= 0) {
            throw new IllegalArgumentException(localizationService.getMessage("test.id.invalid", LocaleContextHolder.getLocale()));
        }

        return testRepositoryImpl.updateTest(test);
    }

    @Override
    public boolean deleteTest(Integer id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException(localizationService.getMessage("test.id.invalid", LocaleContextHolder.getLocale()));
        }

        return testRepositoryImpl.deleteTest(id);
    }

    @Override
    public List<Test> getTestsByCourse(Integer courseId, Map<String, String> params) {
        if (courseId == null || courseId <= 0) {
            throw new IllegalArgumentException(localizationService.getMessage("test.course.invalid", LocaleContextHolder.getLocale()));
        }
        return testRepositoryImpl.getTestsByCourse(courseId,params);
    }

    @Override
    public List<Test> searchTestsByName(String name) {
        return testRepositoryImpl.searchTestsByName(name);
    }

    @Override
    public List<Test> getTestsByCreatedDateRange(Date startDate, Date endDate) {
        return testRepositoryImpl.getTestsByCreatedDateRange(startDate, endDate);
    }


    @Override
    public long countTestsInCourse(Integer courseId) {
        return testRepositoryImpl.countTestsInCourse(courseId);
    }

    @Override
    public boolean isValidTest(Test test) {
        if(test == null) {
            return false;
        }

        if(test.getName() == null || test.getName().isEmpty()) {
            return false;
        }

        if(test.getCreatedByUserId() == null) {
            return false;
        }

        if(test.getCourseId() == null) {
            return false;
        }

        return true;
    }
}
