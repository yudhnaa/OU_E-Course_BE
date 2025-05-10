package com.ou.dto;

import com.ou.pojo.Attachment;
import com.ou.pojo.Lesson;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.LessonAttachment}
 */
public class LessonAttachmentDTO implements Serializable {
    private final Integer id;
    private final Attachment attachmentId;
    private final Lesson lessonId;

    public LessonAttachmentDTO(Integer id, Attachment attachmentId, Lesson lessonId) {
        this.id = id;
        this.attachmentId = attachmentId;
        this.lessonId = lessonId;
    }

    public Integer getId() {
        return id;
    }

    public Attachment getAttachmentId() {
        return attachmentId;
    }

    public Lesson getLessonId() {
        return lessonId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonAttachmentDTO entity = (LessonAttachmentDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.attachmentId, entity.attachmentId) &&
                Objects.equals(this.lessonId, entity.lessonId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attachmentId, lessonId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "attachmentId = " + attachmentId + ", " +
                "lessonId = " + lessonId + ")";
    }
}