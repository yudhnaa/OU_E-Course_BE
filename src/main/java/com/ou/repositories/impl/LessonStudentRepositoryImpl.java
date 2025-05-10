package com.ou.repositories.impl;

import com.ou.pojo.LessonStudent;
import com.ou.repositories.LessonStudentRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import java.util.List;

@Repository
@Transactional
public class LessonStudentRepositoryImpl implements LessonStudentRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public LessonStudent save(LessonStudent lessonStudent) {
        return null;
    }

    @Override
    public List<LessonStudent> findAll() {
        return List.of();
    }

    @Override
    public List<LessonStudent> findByLessonId(Integer lessonId) {
        return List.of();
    }

    @Override
    public List<LessonStudent> findByStudentId(Integer studentId) {
        return List.of();
    }

    @Override
    public List<LessonStudent> findByLearningStatus(Boolean isLearn) {
        return List.of();
    }
}
