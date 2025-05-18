package com.ou.services;

import com.ou.pojo.LessonStudent;

import java.util.List;

public interface LessonStudentService {
    LessonStudent save(LessonStudent lessonStudent);
    List<LessonStudent> findAll();
    List<LessonStudent> findByLessonId(Integer lessonId);
    List<LessonStudent> findByStudentId(Integer studentId);
    List<LessonStudent> findByLearningStatus(Boolean isLearn);
}
