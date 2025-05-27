package com.ou.services;

import com.ou.exceptions.NotFoundException;
import com.ou.pojo.LessonType;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface LessonTypeService {
    // Retrieve a lesson type by ID
    LessonType getLessonTypeById(int id);

    // Add a new lesson type
    LessonType addLessonType(LessonType lessonType);

    // Update an existing lesson type
    LessonType updateLessonType(LessonType lessonType);

    // Delete a lesson type by ID
    boolean deleteLessonType(int id);

    // Retrieve a list of lesson types with optional pagination
    List<LessonType> getLessonTypes();

    // Search for lesson types based on filters and pagination
    List<LessonType> searchLessonTypes();

    // Retrieve a lesson type by name
    Optional<LessonType> getLessonTypeByName(String name);

    // Count total lesson types
    long countLessonTypes();

    // Count search results based on filters
    long countSearchResults(Map<String, String> filters);
}
