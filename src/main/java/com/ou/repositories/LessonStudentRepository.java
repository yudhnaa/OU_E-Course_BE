package com.ou.repositories;

import com.ou.pojo.LessonStudent;

import java.util.List;

public interface LessonStudentRepository {
    LessonStudent updateLessonStudent(LessonStudent lessonStudent);
    List<LessonStudent> getAllLessonStudents();
    List<LessonStudent> findByLessonId(Integer lessonId);
    List<LessonStudent> findByStudentId(Integer studentId);
    List<LessonStudent> findByLearningStatus(Boolean isLearn);
    boolean isLessonCompleted(Integer lessonId, Integer studentId);
    LessonStudent findByLessonIdAndStudentId(Integer lessonId, Integer studentId);
}
