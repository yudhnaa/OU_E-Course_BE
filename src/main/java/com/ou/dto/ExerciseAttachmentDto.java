package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.ExerciseAttachment;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link ExerciseAttachment}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseAttachmentDto implements Serializable {
    private Integer id;
    private Integer attachmentIdId;
    private String attachmentIdName;
    private String attachmentIdLink;
    private String attachmentIdDescription;
    private Integer exerciseIdId;
    private String exerciseIdName;
    private int exerciseIdDurationMinutes;

    public ExerciseAttachmentDto() {
    }

    public ExerciseAttachmentDto(Integer id, Integer attachmentIdId, String attachmentIdName, String attachmentIdLink, String attachmentIdDescription, Integer exerciseIdId, String exerciseIdName, int exerciseIdDurationMinutes) {
        this.id = id;
        this.attachmentIdId = attachmentIdId;
        this.attachmentIdName = attachmentIdName;
        this.attachmentIdLink = attachmentIdLink;
        this.attachmentIdDescription = attachmentIdDescription;
        this.exerciseIdId = exerciseIdId;
        this.exerciseIdName = exerciseIdName;
        this.exerciseIdDurationMinutes = exerciseIdDurationMinutes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseAttachmentDto entity = (ExerciseAttachmentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.attachmentIdId, entity.attachmentIdId) &&
                Objects.equals(this.attachmentIdName, entity.attachmentIdName) &&
                Objects.equals(this.attachmentIdLink, entity.attachmentIdLink) &&
                Objects.equals(this.attachmentIdDescription, entity.attachmentIdDescription) &&
                Objects.equals(this.exerciseIdId, entity.exerciseIdId) &&
                Objects.equals(this.exerciseIdName, entity.exerciseIdName) &&
                Objects.equals(this.exerciseIdDurationMinutes, entity.exerciseIdDurationMinutes);
    }

    public String getAttachmentIdDescription() {
        return attachmentIdDescription;
    }

    public Integer getAttachmentIdId() {
        return attachmentIdId;
    }

    public String getAttachmentIdLink() {
        return attachmentIdLink;
    }

    public String getAttachmentIdName() {
        return attachmentIdName;
    }

    public int getExerciseIdDurationMinutes() {
        return exerciseIdDurationMinutes;
    }

    public Integer getExerciseIdId() {
        return exerciseIdId;
    }

    public String getExerciseIdName() {
        return exerciseIdName;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, attachmentIdId, attachmentIdName, attachmentIdLink, attachmentIdDescription, exerciseIdId, exerciseIdName, exerciseIdDurationMinutes);
    }

    public void setAttachmentIdDescription(String attachmentIdDescription) {
        this.attachmentIdDescription = attachmentIdDescription;
    }

    public void setAttachmentIdId(Integer attachmentIdId) {
        this.attachmentIdId = attachmentIdId;
    }

    public void setAttachmentIdLink(String attachmentIdLink) {
        this.attachmentIdLink = attachmentIdLink;
    }

    public void setAttachmentIdName(String attachmentIdName) {
        this.attachmentIdName = attachmentIdName;
    }

    public void setExerciseIdDurationMinutes(int exerciseIdDurationMinutes) {
        this.exerciseIdDurationMinutes = exerciseIdDurationMinutes;
    }

    public void setExerciseIdId(Integer exerciseIdId) {
        this.exerciseIdId = exerciseIdId;
    }

    public void setExerciseIdName(String exerciseIdName) {
        this.exerciseIdName = exerciseIdName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "attachmentIdId = " + attachmentIdId + ", " +
                "attachmentIdName = " + attachmentIdName + ", " +
                "attachmentIdLink = " + attachmentIdLink + ", " +
                "attachmentIdDescription = " + attachmentIdDescription + ", " +
                "exerciseIdId = " + exerciseIdId + ", " +
                "exerciseIdName = " + exerciseIdName + ", " +
                "exerciseIdDurationMinutes = " + exerciseIdDurationMinutes + ")";
    }
}