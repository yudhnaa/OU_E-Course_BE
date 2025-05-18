package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.LessonAttachment}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonAttachmentDto implements Serializable {
    private final Integer id;
    private final Integer attachmentIdId;
    private final String attachmentIdLink;
    private final Integer lessonIdId;
    private final String lessonIdEmbedLink;

    public LessonAttachmentDto(Integer id, Integer attachmentIdId, String attachmentIdLink, Integer lessonIdId, String lessonIdEmbedLink) {
        this.id = id;
        this.attachmentIdId = attachmentIdId;
        this.attachmentIdLink = attachmentIdLink;
        this.lessonIdId = lessonIdId;
        this.lessonIdEmbedLink = lessonIdEmbedLink;
    }

    public Integer getId() {
        return id;
    }

    public Integer getAttachmentIdId() {
        return attachmentIdId;
    }

    public String getAttachmentIdLink() {
        return attachmentIdLink;
    }

    public Integer getLessonIdId() {
        return lessonIdId;
    }

    public String getLessonIdEmbedLink() {
        return lessonIdEmbedLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonAttachmentDto entity = (LessonAttachmentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.attachmentIdId, entity.attachmentIdId) &&
                Objects.equals(this.attachmentIdLink, entity.attachmentIdLink) &&
                Objects.equals(this.lessonIdId, entity.lessonIdId) &&
                Objects.equals(this.lessonIdEmbedLink, entity.lessonIdEmbedLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attachmentIdId, attachmentIdLink, lessonIdId, lessonIdEmbedLink);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "attachmentIdId = " + attachmentIdId + ", " +
                "attachmentIdLink = " + attachmentIdLink + ", " +
                "lessonIdId = " + lessonIdId + ", " +
                "lessonIdEmbedLink = " + lessonIdEmbedLink + ")";
    }
}