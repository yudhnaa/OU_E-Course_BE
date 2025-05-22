package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.Lecturer;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Lecturer}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LecturerDto implements Serializable {
    private Integer id;
    private Integer userIdId;
    private String userIdLastName;
    private String userIdFirstName;
    private String userIdUsername;
    private String userIdAvatar;
    private String userIdPublicId;
    private String userIdEmail;
    private Long countCourse;
    private Long countLesson;

    public LecturerDto() {
    }

    public LecturerDto(Integer id, Integer userIdId, String userIdLastName, String userIdFirstName, String userIdUsername, String userIdAvatar, String userIdPublicId, String userIdEmail, Long countCourse, Long countLesson) {
        this.id = id;
        this.userIdId = userIdId;
        this.userIdLastName = userIdLastName;
        this.userIdFirstName = userIdFirstName;
        this.userIdUsername = userIdUsername;
        this.userIdAvatar = userIdAvatar;
        this.userIdPublicId = userIdPublicId;
        this.userIdEmail = userIdEmail;
        this.countCourse = countCourse;
        this.countLesson = countLesson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LecturerDto entity = (LecturerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.userIdId, entity.userIdId) &&
                Objects.equals(this.userIdLastName, entity.userIdLastName) &&
                Objects.equals(this.userIdFirstName, entity.userIdFirstName) &&
                Objects.equals(this.userIdUsername, entity.userIdUsername) &&
                Objects.equals(this.userIdAvatar, entity.userIdAvatar) &&
                Objects.equals(this.userIdPublicId, entity.userIdPublicId) &&
                Objects.equals(this.userIdEmail, entity.userIdEmail) &&
                Objects.equals(this.countCourse, entity.countCourse) &&
                Objects.equals(this.countLesson, entity.countLesson);
    }

    public Long getCountCourse() {
        return countCourse;
    }

    public void setCountCourse(Long countCourse) {
        this.countCourse = countCourse;
    }

    public Long getCountLesson() {
        return countLesson;
    }

    public void setCountLesson(Long countLesson) {
        this.countLesson = countLesson;
    }

    public Integer getId() {
        return id;
    }

    public String getUserIdAvatar() {
        return userIdAvatar;
    }

    public String getUserIdEmail() {
        return userIdEmail;
    }

    public String getUserIdFirstName() {
        return userIdFirstName;
    }

    public Integer getUserIdId() {
        return userIdId;
    }

    public String getUserIdLastName() {
        return userIdLastName;
    }

    public String getUserIdPublicId() {
        return userIdPublicId;
    }

    public String getUserIdUsername() {
        return userIdUsername;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userIdId, userIdLastName, userIdFirstName, userIdUsername, userIdAvatar, userIdPublicId, userIdEmail);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserIdAvatar(String userIdAvatar) {
        this.userIdAvatar = userIdAvatar;
    }

    public void setUserIdEmail(String userIdEmail) {
        this.userIdEmail = userIdEmail;
    }

    public void setUserIdFirstName(String userIdFirstName) {
        this.userIdFirstName = userIdFirstName;
    }

    public void setUserIdId(Integer userIdId) {
        this.userIdId = userIdId;
    }

    public void setUserIdLastName(String userIdLastName) {
        this.userIdLastName = userIdLastName;
    }

    public void setUserIdPublicId(String userIdPublicId) {
        this.userIdPublicId = userIdPublicId;
    }

    public void setUserIdUsername(String userIdUsername) {
        this.userIdUsername = userIdUsername;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "userIdId = " + userIdId + ", " +
                "userIdLastName = " + userIdLastName + ", " +
                "userIdFirstName = " + userIdFirstName + ", " +
                "userIdUsername = " + userIdUsername + ", " +
                "userIdAvatar = " + userIdAvatar + ", " +
                "userIdPublicId = " + userIdPublicId + ", " +
                "userIdEmail = " + userIdEmail + ", " +
                "countCourse = " + countCourse + ", " +
                "countLesson = " + countLesson + ")";
    }
}