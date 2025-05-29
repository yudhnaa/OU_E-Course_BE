package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.Student}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentSignUpFormDto implements Serializable {
    private Integer id;
    private Integer userIdId;
    private String userIdLastName;
    private String userIdFirstName;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate userIdBirthday;
    private String userIdUsername;
    private String userIdPassword;
    private String userIdEmail;
    private Integer userIdUserRoleIdId;
    private MultipartFile avatarFile;

    public StudentSignUpFormDto() {
    }

    public StudentSignUpFormDto(Integer id, Integer userIdId, String userIdLastName, String userIdFirstName, LocalDate userIdBirthday, String userIdUsername, String userIdPassword, String userIdEmail, Integer userIdUserRoleIdId, MultipartFile avatarFile) {
        this.id = id;
        this.userIdId = userIdId;
        this.userIdLastName = userIdLastName;
        this.userIdFirstName = userIdFirstName;
        this.userIdBirthday = userIdBirthday;
        this.userIdUsername = userIdUsername;
        this.userIdPassword = userIdPassword;
        this.userIdEmail = userIdEmail;
        this.userIdUserRoleIdId = userIdUserRoleIdId;
        this.avatarFile = avatarFile;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentSignUpFormDto entity = (StudentSignUpFormDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.userIdId, entity.userIdId) &&
                Objects.equals(this.userIdLastName, entity.userIdLastName) &&
                Objects.equals(this.userIdFirstName, entity.userIdFirstName) &&
                Objects.equals(this.userIdBirthday, entity.userIdBirthday) &&
                Objects.equals(this.userIdUsername, entity.userIdUsername) &&
                Objects.equals(this.userIdPassword, entity.userIdPassword) &&
                Objects.equals(this.userIdEmail, entity.userIdEmail) &&
                Objects.equals(this.avatarFile, entity.avatarFile) &&
                Objects.equals(this.userIdUserRoleIdId, entity.userIdUserRoleIdId);
    }

    public MultipartFile getAvatarFile() {
        return avatarFile;
    }

    public void setAvatarFile(MultipartFile avatarFile) {
        this.avatarFile = avatarFile;
    }

    public Integer getId() {
        return id;
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

    public Integer getUserIdId() {
        return userIdId;
    }

    public String getUserIdLastName() {
        return userIdLastName;
    }

    public String getUserIdPassword() {
        return userIdPassword;
    }

    public Integer getUserIdUserRoleIdId() {
        return userIdUserRoleIdId;
    }

    public String getUserIdUsername() {
        return userIdUsername;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userIdId, userIdLastName, userIdFirstName, userIdBirthday, userIdUsername, userIdPassword, userIdEmail, userIdUserRoleIdId);
    }

    public void setId(Integer id) {
        this.id = id;
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

    public void setUserIdId(Integer userIdId) {
        this.userIdId = userIdId;
    }

    public void setUserIdLastName(String userIdLastName) {
        this.userIdLastName = userIdLastName;
    }

    public void setUserIdPassword(String userIdPassword) {
        this.userIdPassword = userIdPassword;
    }

    public void setUserIdUserRoleIdId(Integer userIdUserRoleIdId) {
        this.userIdUserRoleIdId = userIdUserRoleIdId;
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
                "userIdBirthday = " + userIdBirthday + ", " +
                "userIdUsername = " + userIdUsername + ", " +
                "userIdPassword = " + userIdPassword + ", " +
                "userIdEmail = " + userIdEmail + ", " +
                "userIdUserRoleIdId = " + userIdUserRoleIdId + ")";
    }
}