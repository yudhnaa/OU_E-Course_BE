package com.ou.repositories.impl;


import com.ou.pojo.LessonAttachment;
import com.ou.repositories.LessonAttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class LessonAttachmentRepositoryImpl implements LessonAttachmentRepository {
    @Autowired
    private LocalSessionFactoryBean factory;

    @Override
    public LessonAttachment createLessonAttachment(LessonAttachment lessonAttachment) {
        return null;
    }

    @Override
    public LessonAttachment getLessonAttachmentById(Integer id) {
        return null;
    }

    @Override
    public Optional<LessonAttachment> getAllLessonAttachments() {
        return Optional.empty();
    }

    @Override
    public LessonAttachment updateLessonAttachment(LessonAttachment lessonAttachment) {
        return null;
    }

    @Override
    public boolean deleteLessonAttachment(Integer id) {
        return false;
    }
}
