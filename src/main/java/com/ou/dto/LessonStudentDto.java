package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.LessonStudent}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonStudentDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private final String name;
    private final Boolean isLearn;
    private final Integer lessonIdId;
    private final String lessonIdName;
    private final String lessonIdEmbedLink;
    private final Integer studentIdId;
    private final String studentIdUsername;
    private final String studentIdAvatar;
    private final String studentIdEmail;
    private final boolean studentIdIsActive;

    public LessonStudentDto(Integer id, String name, Boolean isLearn, Integer lessonIdId, String lessonIdName, String lessonIdEmbedLink, Integer studentIdId, String studentIdUsername, String studentIdAvatar, String studentIdEmail, boolean studentIdIsActive) {
        this.id = id;
        this.name = name;
        this.isLearn = isLearn;
        this.lessonIdId = lessonIdId;
        this.lessonIdName = lessonIdName;
        this.lessonIdEmbedLink = lessonIdEmbedLink;
        this.studentIdId = studentIdId;
        this.studentIdUsername = studentIdUsername;
        this.studentIdAvatar = studentIdAvatar;
        this.studentIdEmail = studentIdEmail;
        this.studentIdIsActive = studentIdIsActive;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Boolean getIsLearn() {
        return isLearn;
    }

    public Integer getLessonIdId() {
        return lessonIdId;
    }

    public String getLessonIdName() {
        return lessonIdName;
    }

    public String getLessonIdEmbedLink() {
        return lessonIdEmbedLink;
    }

    public Integer getStudentIdId() {
        return studentIdId;
    }

    public String getStudentIdUsername() {
        return studentIdUsername;
    }

    public String getStudentIdAvatar() {
        return studentIdAvatar;
    }

    public String getStudentIdEmail() {
        return studentIdEmail;
    }

    public boolean getStudentIdIsActive() {
        return studentIdIsActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonStudentDto entity = (LessonStudentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.isLearn, entity.isLearn) &&
                Objects.equals(this.lessonIdId, entity.lessonIdId) &&
                Objects.equals(this.lessonIdName, entity.lessonIdName) &&
                Objects.equals(this.lessonIdEmbedLink, entity.lessonIdEmbedLink) &&
                Objects.equals(this.studentIdId, entity.studentIdId) &&
                Objects.equals(this.studentIdUsername, entity.studentIdUsername) &&
                Objects.equals(this.studentIdAvatar, entity.studentIdAvatar) &&
                Objects.equals(this.studentIdEmail, entity.studentIdEmail) &&
                Objects.equals(this.studentIdIsActive, entity.studentIdIsActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isLearn, lessonIdId, lessonIdName, lessonIdEmbedLink, studentIdId, studentIdUsername, studentIdAvatar, studentIdEmail, studentIdIsActive);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "isLearn = " + isLearn + ", " +
                "lessonIdId = " + lessonIdId + ", " +
                "lessonIdName = " + lessonIdName + ", " +
                "lessonIdEmbedLink = " + lessonIdEmbedLink + ", " +
                "studentIdId = " + studentIdId + ", " +
                "studentIdUsername = " + studentIdUsername + ", " +
                "studentIdAvatar = " + studentIdAvatar + ", " +
                "studentIdEmail = " + studentIdEmail + ", " +
                "studentIdIsActive = " + studentIdIsActive + ")";
    }
}