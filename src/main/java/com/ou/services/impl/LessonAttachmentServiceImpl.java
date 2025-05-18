package com.ou.services.impl;

import com.ou.pojo.LessonAttachment;
import com.ou.repositories.LessonAttachmentRepository;
import com.ou.services.LessonAttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonAttachmentServiceImpl implements LessonAttachmentService {
    @Autowired
    private LessonAttachmentRepository lessonAttachmentRepo;

    @Override
    public LessonAttachment createLessonAttachment(LessonAttachment lessonAttachment) {
        return null;
    }

    @Override
    public LessonAttachment getLessonAttachmentById(Integer id) {
        return null;
    }

    @Override
    public List<LessonAttachment> getAllLessonAttachments() {
        return List.of();
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
