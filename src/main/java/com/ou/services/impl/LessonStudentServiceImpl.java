package com.ou.services.impl;

import com.ou.pojo.LessonStudent;
import com.ou.repositories.LessonStudentRepository;
import com.ou.services.LessonStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class LessonStudentServiceImpl implements LessonStudentService {
    @Autowired
    private LessonStudentRepository lessonStudentRepo;

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
