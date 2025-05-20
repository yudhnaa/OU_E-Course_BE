package com.ou.services.impl;

import com.ou.exceptions.NotFoundException;
import com.ou.mappers.CloudinaryHelper;
import com.ou.pojo.LessonAttachment;
import com.ou.repositories.LessonAttachmentRepository;
import com.ou.services.AttachmentService;
import com.ou.services.LessonAttachmentService;
import com.ou.services.LessonService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class LessonAttachmentServiceImpl implements LessonAttachmentService {
    @Autowired
    private LessonAttachmentRepository lessonAttachmentRepo;
    
    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private CloudinaryHelper cloudinaryHelper;

    @Override
    public LessonAttachment createLessonAttachment(LessonAttachment lessonAttachment) {
        LessonAttachment res = lessonAttachmentRepo.createLessonAttachment(lessonAttachment);

        if (res == null) {
            throw new NotFoundException(localizationService.getMessage("lessonAttachment.notFound", LocaleContextHolder.getLocale()));
        }

        return res;
    }

    @Override
    public LessonAttachment getLessonAttachmentById(Integer id) {
        LessonAttachment lessonAttachment = lessonAttachmentRepo.getLessonAttachmentById(id);
        if (lessonAttachment == null) {
            throw new NotFoundException(localizationService.getMessage("lessonAttachment.notFound", LocaleContextHolder.getLocale()));
        }
        return lessonAttachment;
    }

    @Override
    public List<LessonAttachment> getAllLessonAttachments() {
        return lessonAttachmentRepo.getAllLessonAttachments();
    }

    @Override
    public List<LessonAttachment> getLessonAttachmentsByLesson(Integer lessonId) {
        // Verify that the lesson exists before fetching attachments
        if (lessonService.getLessonById(lessonId).isEmpty()) {
            throw new NotFoundException(localizationService.getMessage("lesson.notFound", LocaleContextHolder.getLocale()));
        }

        List<LessonAttachment> res = lessonAttachmentRepo.getLessonAttachmentsByLesson(lessonId);

        return res;
    }

    @Override
    public LessonAttachment updateLessonAttachment(LessonAttachment lessonAttachment) {
        // Verify that the lessonAttachment exists before updating
        getLessonAttachmentById(lessonAttachment.getId());
        return lessonAttachmentRepo.updateLessonAttachment(lessonAttachment);
    }

    @Override
    public boolean deleteLessonAttachment(Integer id) throws IOException {
        // Verify that the lessonAttachment exists before deleting
        LessonAttachment cur = getLessonAttachmentById(id);
        if (cur == null) {
            throw new NotFoundException(localizationService.getMessage("lessonAttachment.notFound", LocaleContextHolder.getLocale()));
        }

        boolean res = attachmentService.deleteAttachment(cur.getAttachmentId().getId());

        if (!res) {
            throw new NotFoundException(localizationService.getMessage("attachment.notFound", LocaleContextHolder.getLocale()));
        }

        return lessonAttachmentRepo.deleteLessonAttachment(id);
    }

    @Override
    public long countLessonAttachmentsByLesson(Integer lessonId) {

        // Verify that the lesson exists before counting attachments
        if (lessonService.getLessonById(lessonId) == null) {
            throw new NotFoundException(localizationService.getMessage("lesson.notFound", LocaleContextHolder.getLocale()));
        }

        return lessonAttachmentRepo.countLessonAttachmentsByLesson(lessonId);
    }
}
