package com.ou.repositories;

import com.ou.pojo.LessonType;

import java.util.List;

public interface LessonTypeRepository {
    LessonType createLessonType(LessonType lessonType);
    LessonType getLessonTypeById(Integer id);
    List<LessonType> getAllLessonTypes();
    LessonType updateLessonType(LessonType lessonType);
    boolean deleteLessonType(Integer id);
}
