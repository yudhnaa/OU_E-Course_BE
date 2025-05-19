package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.Lesson}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 65535)
    private String name;
    @NotNull
    @Size(min = 1, max = 255)
    private String embedLink;
    @Size(max = 65535)
    private String description;
    @Size(max = 255)
    private String image;
    private Integer courseIdId;
    private String courseIdName;
    private Integer lessonTypeIdId;
    private String lessonTypeIdName;
    private Integer userUploadIdId;
    private String userUploadIdLastName;
    private String userUploadIdFirstName;
    private String userUploadIdUsername;
    private long countAttachment;
    private long countExercise;


    public LessonDto() {
    }

    public LessonDto(Integer id, String name, String embedLink, String description, String image, Integer courseIdId, String courseIdName, Integer lessonTypeIdId, String lessonTypeIdName, Integer userUploadIdId, String userUploadIdLastName, String userUploadIdFirstName, String userUploadIdUsername) {
        this.id = id;
        this.name = name;
        this.embedLink = embedLink;
        this.description = description;
        this.image = image;
        this.courseIdId = courseIdId;
        this.courseIdName = courseIdName;
        this.lessonTypeIdId = lessonTypeIdId;
        this.lessonTypeIdName = lessonTypeIdName;
        this.userUploadIdId = userUploadIdId;
        this.userUploadIdLastName = userUploadIdLastName;
        this.userUploadIdFirstName = userUploadIdFirstName;
        this.userUploadIdUsername = userUploadIdUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonDto entity = (LessonDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.embedLink, entity.embedLink) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.image, entity.image) &&
                Objects.equals(this.courseIdId, entity.courseIdId) &&
                Objects.equals(this.courseIdName, entity.courseIdName) &&
                Objects.equals(this.lessonTypeIdId, entity.lessonTypeIdId) &&
                Objects.equals(this.lessonTypeIdName, entity.lessonTypeIdName) &&
                Objects.equals(this.userUploadIdId, entity.userUploadIdId) &&
                Objects.equals(this.userUploadIdLastName, entity.userUploadIdLastName) &&
                Objects.equals(this.userUploadIdFirstName, entity.userUploadIdFirstName) &&
                Objects.equals(this.userUploadIdUsername, entity.userUploadIdUsername);
    }

    public long getCountAttachment() {
        return countAttachment;
    }

    public void setCountAttachment(long countAttachment) {
        this.countAttachment = countAttachment;
    }

    public long getCountExercise() {
        return countExercise;
    }

    public void setCountExercise(long countExercise) {
        this.countExercise = countExercise;
    }

    public Integer getCourseIdId() {
        return courseIdId;
    }

    public String getCourseIdName() {
        return courseIdName;
    }

    public String getDescription() {
        return description;
    }

    public String getEmbedLink() {
        return embedLink;
    }

    public Integer getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public Integer getLessonTypeIdId() {
        return lessonTypeIdId;
    }

    public String getLessonTypeIdName() {
        return lessonTypeIdName;
    }

    public String getName() {
        return name;
    }

    public String getUserUploadIdFirstName() {
        return userUploadIdFirstName;
    }

    public Integer getUserUploadIdId() {
        return userUploadIdId;
    }

    public String getUserUploadIdLastName() {
        return userUploadIdLastName;
    }

    public String getUserUploadIdUsername() {
        return userUploadIdUsername;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, embedLink, description, image, courseIdId, courseIdName, lessonTypeIdId, lessonTypeIdName, userUploadIdId, userUploadIdLastName, userUploadIdFirstName, userUploadIdUsername);
    }

    public void setCourseIdId(Integer courseIdId) {
        this.courseIdId = courseIdId;
    }

    public void setCourseIdName(String courseIdName) {
        this.courseIdName = courseIdName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmbedLink(String embedLink) {
        this.embedLink = embedLink;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLessonTypeIdId(Integer lessonTypeIdId) {
        this.lessonTypeIdId = lessonTypeIdId;
    }

    public void setLessonTypeIdName(String lessonTypeIdName) {
        this.lessonTypeIdName = lessonTypeIdName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserUploadIdFirstName(String userUploadIdFirstName) {
        this.userUploadIdFirstName = userUploadIdFirstName;
    }

    public void setUserUploadIdId(Integer userUploadIdId) {
        this.userUploadIdId = userUploadIdId;
    }

    public void setUserUploadIdLastName(String userUploadIdLastName) {
        this.userUploadIdLastName = userUploadIdLastName;
    }

    public void setUserUploadIdUsername(String userUploadIdUsername) {
        this.userUploadIdUsername = userUploadIdUsername;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "embedLink = " + embedLink + ", " +
                "description = " + description + ", " +
                "image = " + image + ", " +
                "courseIdId = " + courseIdId + ", " +
                "courseIdName = " + courseIdName + ", " +
                "lessonTypeIdId = " + lessonTypeIdId + ", " +
                "lessonTypeIdName = " + lessonTypeIdName + ", " +
                "userUploadIdId = " + userUploadIdId + ", " +
                "userUploadIdLastName = " + userUploadIdLastName + ", " +
                "userUploadIdFirstName = " + userUploadIdFirstName + ", " +
                "userUploadIdUsername = " + userUploadIdUsername + ")";
    }
}