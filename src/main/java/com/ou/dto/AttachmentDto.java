package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.Attachment;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link Attachment}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AttachmentDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    @NotNull
    @Size(min = 1, max = 255)
    private String link;
    @Size(max = 65535)
    private String description;
    private Set<Integer> exerciseAttachmentSetIds;
    private Set<Integer> exerciseSetIds;
    private Set<Integer> lessonAttachmentSetIds;

    public AttachmentDto() {
    }

    public AttachmentDto(Integer id, String name, String link, String description, Set<Integer> exerciseAttachmentSetIds, Set<Integer> exerciseSetIds, Set<Integer> lessonAttachmentSetIds) {
        this.id = id;
        this.name = name;
        this.link = link;
        this.description = description;
        this.exerciseAttachmentSetIds = exerciseAttachmentSetIds;
        this.exerciseSetIds = exerciseSetIds;
        this.lessonAttachmentSetIds = lessonAttachmentSetIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AttachmentDto entity = (AttachmentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.link, entity.link) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.exerciseAttachmentSetIds, entity.exerciseAttachmentSetIds) &&
                Objects.equals(this.exerciseSetIds, entity.exerciseSetIds) &&
                Objects.equals(this.lessonAttachmentSetIds, entity.lessonAttachmentSetIds);
    }

    public String getDescription() {
        return description;
    }

    public Set<Integer> getExerciseAttachmentSetIds() {
        return exerciseAttachmentSetIds;
    }

    public Set<Integer> getExerciseSetIds() {
        return exerciseSetIds;
    }

    public Integer getId() {
        return id;
    }

    public Set<Integer> getLessonAttachmentSetIds() {
        return lessonAttachmentSetIds;
    }

    public String getLink() {
        return link;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, link, description, exerciseAttachmentSetIds, exerciseSetIds, lessonAttachmentSetIds);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExerciseAttachmentSetIds(Set<Integer> exerciseAttachmentSetIds) {
        this.exerciseAttachmentSetIds = exerciseAttachmentSetIds;
    }

    public void setExerciseSetIds(Set<Integer> exerciseSetIds) {
        this.exerciseSetIds = exerciseSetIds;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLessonAttachmentSetIds(Set<Integer> lessonAttachmentSetIds) {
        this.lessonAttachmentSetIds = lessonAttachmentSetIds;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "link = " + link + ", " +
                "description = " + description + ", " +
                "exerciseAttachmentSetIds = " + exerciseAttachmentSetIds + ", " +
                "exerciseSetIds = " + exerciseSetIds + ", " +
                "lessonAttachmentSetIds = " + lessonAttachmentSetIds + ")";
    }
}