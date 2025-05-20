package com.ou.services;

import com.ou.pojo.LessonAttachment;

import java.io.IOException;
import java.util.List;

public interface LessonAttachmentService {
    // create
    LessonAttachment createLessonAttachment(LessonAttachment lessonAttachment);

    // read
    LessonAttachment getLessonAttachmentById(Integer id);
    List<LessonAttachment> getAllLessonAttachments();
    List<LessonAttachment> getLessonAttachmentsByLesson(Integer lessonId);

    // update
    LessonAttachment updateLessonAttachment(LessonAttachment lessonAttachment);

    // delete
    boolean deleteLessonAttachment(Integer id) throws IOException;

    //count
    long countLessonAttachmentsByLesson(Integer lessonId);
}
