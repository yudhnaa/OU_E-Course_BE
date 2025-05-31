package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.Lesson}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonWithAttachmentsDto extends LessonDto implements Serializable {
    List<AttachmentDto> attachmentDtos;

    public LessonWithAttachmentsDto() {
    }

    public LessonWithAttachmentsDto(Integer id, String name, String embedLink, String description, String image, Integer courseIdId, String courseIdName, Integer lessonTypeIdId, String lessonTypeIdName, Integer userUploadIdId, String userUploadIdLastName, String userUploadIdFirstName, String userUploadIdUsername, long countAttachment, long countExercise, int orderIndex, List<AttachmentDto> attachmentDtos) {
        super(id, name, embedLink, description, image, courseIdId, courseIdName, lessonTypeIdId, lessonTypeIdName, userUploadIdId, userUploadIdLastName, userUploadIdFirstName, userUploadIdUsername, countAttachment, countExercise, orderIndex);
        this.attachmentDtos = attachmentDtos;
    }

    public List<AttachmentDto> getAttachmentDtos() {
        return attachmentDtos;
    }

    public void setAttachmentDtos(List<AttachmentDto> attachmentDtos) {
        this.attachmentDtos = attachmentDtos;
    }
}