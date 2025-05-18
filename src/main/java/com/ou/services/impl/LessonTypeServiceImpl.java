package com.ou.services.impl;


import com.ou.pojo.LessonType;
import com.ou.repositories.LessonTypeRepository;
import com.ou.services.LessonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonTypeServiceImpl implements LessonTypeService {
    @Autowired
    private LessonTypeRepository lessonTypeRepo;

    @Override
    public LessonType createLessonType(LessonType lessonType) {
        return null;
    }

    @Override
    public LessonType getLessonTypeById(Integer id) {
        return null;
    }

    @Override
    public List<LessonType> getAllLessonTypes() {
        return List.of();
    }

    @Override
    public LessonType updateLessonType(LessonType lessonType) {
        return null;
    }

    @Override
    public boolean deleteLessonType(Integer id) {
        return false;
    }
}
