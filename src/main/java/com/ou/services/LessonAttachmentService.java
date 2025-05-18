package com.ou.services;

import com.ou.pojo.LessonAttachment;
import java.util.List;

public interface LessonAttachmentService {
    LessonAttachment createLessonAttachment(LessonAttachment lessonAttachment);
    LessonAttachment getLessonAttachmentById(Integer id);
    List<LessonAttachment> getAllLessonAttachments();
    LessonAttachment updateLessonAttachment(LessonAttachment lessonAttachment);
    boolean deleteLessonAttachment(Integer id);
}
