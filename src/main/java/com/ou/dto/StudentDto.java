package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.User;
import jakarta.mail.Multipart;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link User}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDto implements Serializable {
    private Integer id;
    private Integer userIdId;
    @NotNull
    @Size(min = 1, max = 50)
    private String userIdLastName;
    @NotNull
    @Size(min = 1, max = 50)
    private String userIdFirstName;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate userIdBirthday;
    @NotNull
    @Size(min = 1, max = 50)
    private String userIdUsername;
    @NotNull
    @Size(min = 1, max = 100)
    private String userIdEmail;
    private Integer userRoleIdId;
    private String userRoleIdName;
    private String userIdAvatar;
    private long countCourse;



    public StudentDto() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto entity = (StudentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.userIdLastName, entity.userIdLastName) &&
                Objects.equals(this.userIdFirstName, entity.userIdFirstName) &&
                Objects.equals(this.userIdBirthday, entity.userIdBirthday) &&
                Objects.equals(this.userIdUsername, entity.userIdUsername) &&
                Objects.equals(this.userIdEmail, entity.userIdEmail) &&
                Objects.equals(this.userRoleIdId, entity.userRoleIdId) &&
                Objects.equals(this.userRoleIdName, entity.userRoleIdName) &&
                Objects.equals(this.userIdAvatar, entity.userIdAvatar);
    }


    public LocalDate getUserIdBirthday() {
        return userIdBirthday;
    }

    public String getUserIdEmail() {
        return userIdEmail;
    }

    public String getUserIdFirstName() {
        return userIdFirstName;
    }

    public Integer getId() {
        return id;
    }

    public String getUserIdLastName() {
        return userIdLastName;
    }

    public Integer getUserRoleIdId() {
        return userRoleIdId;
    }

    public String getUserRoleIdName() {
        return userRoleIdName;
    }

    public String getUserIdUsername() {
        return userIdUsername;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userIdId, userIdLastName, userIdFirstName, userIdBirthday, userIdUsername, userIdEmail, userRoleIdId, userRoleIdName, userIdAvatar);
    }

    public long getCountCourse() {
        return countCourse;
    }

    public void setCountCourse(long countCourse) {
        this.countCourse = countCourse;
    }

    public void setUserIdBirthday(LocalDate userIdBirthday) {
        this.userIdBirthday = userIdBirthday;
    }

    public void setUserIdEmail(String userIdEmail) {
        this.userIdEmail = userIdEmail;
    }

    public void setUserIdFirstName(String userIdFirstName) {
        this.userIdFirstName = userIdFirstName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserIdLastName(String userIdLastName) {
        this.userIdLastName = userIdLastName;
    }

    public void setUserRoleIdId(Integer userRoleIdId) {
        this.userRoleIdId = userRoleIdId;
    }

    public void setUserRoleIdName(String userRoleIdName) {
        this.userRoleIdName = userRoleIdName;
    }

    public void setUserIdUsername(String userIdUsername) {
        this.userIdUsername = userIdUsername;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "userIdId = " + userIdId + ", " +
                "lastName = " + userIdLastName + ", " +
                "firstName = " + userIdFirstName + ", " +
                "birthday = " + userIdBirthday + ", " +
                "username = " + userIdUsername + ", " +
                "email = " + userIdEmail + ", " +
                "userRoleIdId = " + userRoleIdId + ", " +
                "userRoleIdName = " + userRoleIdName + ")";
    }

    public String getUserIdAvatar() {
        return userIdAvatar;
    }

    public void setUserIdAvatar(String userIdAvatar) {
        this.userIdAvatar = userIdAvatar;
    }

    public Integer getUserIdId() {
        return userIdId;
    }

    public void setUserIdId(Integer userIdId) {
        this.userIdId = userIdId;
    }
}
