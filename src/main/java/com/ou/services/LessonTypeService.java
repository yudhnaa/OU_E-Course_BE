package com.ou.services;

import com.ou.pojo.LessonType;

import java.util.List;

public interface LessonTypeService {
    LessonType createLessonType(LessonType lessonType);
    LessonType getLessonTypeById(Integer id);
    List<LessonType> getAllLessonTypes();
    LessonType updateLessonType(LessonType lessonType);
    boolean deleteLessonType(Integer id);
}
