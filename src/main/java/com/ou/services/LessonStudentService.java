package com.ou.services;

import com.ou.pojo.LessonStudent;

import java.util.List;
import java.util.Optional;

public interface LessonStudentService {
    LessonStudent save(LessonStudent lessonStudent);
    List<LessonStudent> findAll();
    List<LessonStudent> findByLessonId(Integer lessonId);
    List<LessonStudent> findByStudentId(Integer studentId);
    List<LessonStudent> findByLearningStatus(Boolean isLearn);

    boolean isLessonCompleted(Integer lessonId, Integer studentId);

    // mark lessons as learned methods
    LessonStudent markLessonAsLearned(Integer lessonId, Integer studentId) throws Exception;
    Optional<LessonStudent> getLessonStudentByLessonAndStudent(Integer lessonId, Integer studentId);
}
