package com.ou.services;

import com.ou.pojo.LessonAttachment;
import java.util.List;

public interface LessonAttachmentService {
    // create
    LessonAttachment createLessonAttachment(LessonAttachment lessonAttachment);

    // read
    LessonAttachment getLessonAttachmentById(Integer id);
    List<LessonAttachment> getAllLessonAttachments();

    // update
    LessonAttachment updateLessonAttachment(LessonAttachment lessonAttachment);

    // delete
    boolean deleteLessonAttachment(Integer id);

    //count
    long countLessonAttachmentsByLesson(Integer lessonId);
}
