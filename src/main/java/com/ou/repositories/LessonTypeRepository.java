package com.ou.repositories;

import com.ou.pojo.LessonType;

import java.util.List;
import java.util.Optional;

public interface LessonTypeRepository {
    // Create operation
    LessonType createLessonType(LessonType lessonType);
    
    // Read operations
    LessonType getLessonTypeById(Integer id);
    List<LessonType> getAllLessonTypes();
    Optional<LessonType> getLessonTypeByName(String name);
    
    // Update operation
    LessonType updateLessonType(LessonType lessonType);
    
    // Delete operation
    boolean deleteLessonType(Integer id);
}
