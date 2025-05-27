package com.ou.repositories;

import com.ou.pojo.LessonAttachment;
import java.util.List;
import java.util.Optional;

public interface LessonAttachmentRepository {

    LessonAttachment createLessonAttachment(LessonAttachment lessonAttachment);

    LessonAttachment getLessonAttachmentById(Integer id);
    List<LessonAttachment> getAllLessonAttachments();
    List<LessonAttachment> getLessonAttachmentsByLesson(Integer lessonId);

    LessonAttachment updateLessonAttachment(LessonAttachment lessonAttachment);

    boolean deleteLessonAttachment(Integer id);

    long countLessonAttachmentsByLesson(Integer lessonId);
}
