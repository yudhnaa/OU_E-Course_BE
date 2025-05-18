package com.ou.repositories.impl;


import com.ou.pojo.LessonType;
import com.ou.repositories.LessonTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class LessonTypeRepositoryImpl implements LessonTypeRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

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
