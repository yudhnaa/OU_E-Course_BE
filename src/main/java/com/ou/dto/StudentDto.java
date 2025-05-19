package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.User;
import com.ou.pojo.UserRole;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link User}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private String lastName;
    @NotNull
    @Size(min = 1, max = 50)
    private String firstName;
    @NotNull
    private Date birthday;
    @NotNull
    @Size(min = 1, max = 50)
    private String username;
    @NotNull
    @Size(min = 1, max = 255)
    private String password;
    @Size(max = 255)
    private String avatar;
    @NotNull
    @Size(min = 1, max = 100)
    private String email;
    private Integer userRoleIdId;
    private String userRoleIdName;

    public StudentDto() {
    }

    public StudentDto(Integer id, String lastName, String firstName, Date birthday, String username, String password, String avatar, String email, Integer userRoleIdId, String userRoleIdName) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.username = username;
        this.password = password;
        this.avatar = avatar;
        this.email = email;
        this.userRoleIdId = userRoleIdId;
        this.userRoleIdName = userRoleIdName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentDto entity = (StudentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.birthday, entity.birthday) &&
                Objects.equals(this.username, entity.username) &&
                Objects.equals(this.password, entity.password) &&
                Objects.equals(this.avatar, entity.avatar) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.userRoleIdId, entity.userRoleIdId) &&
                Objects.equals(this.userRoleIdName, entity.userRoleIdName);
    }

    public String getAvatar() {
        return avatar;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Integer getUserRoleIdId() {
        return userRoleIdId;
    }

    public String getUserRoleIdName() {
        return userRoleIdName;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, birthday, username, password, avatar, email, userRoleIdId, userRoleIdName);
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserRoleIdId(Integer userRoleIdId) {
        this.userRoleIdId = userRoleIdId;
    }

    public void setUserRoleIdName(String userRoleIdName) {
        this.userRoleIdName = userRoleIdName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "lastName = " + lastName + ", " +
                "firstName = " + firstName + ", " +
                "birthday = " + birthday + ", " +
                "username = " + username + ", " +
                "password = " + password + ", " +
                "avatar = " + avatar + ", " +
                "email = " + email + ", " +
                "userRoleIdId = " + userRoleIdId + ", " +
                "userRoleIdName = " + userRoleIdName + ")";
    }

//    public static User toEntity(StudentDto dto) {
//        if (dto == null) {
//            return null;
//        }
//        User user = new User();
//        user.setId(dto.getId());
//        user.setLastName(dto.getLastName());
//        user.setFirstName(dto.getFirstName());
//        user.setBirthday(dto.getBirthday());
//        user.setUsername(dto.getUsername());
//        user.setPassword(dto.getPassword());
//        user.setAvatar(dto.getAvatar());
//        user.setEmail(dto.getEmail());
//        if (dto.getUserRoleIdId() != null) {
//            UserRole userRole = new UserRole();
//            userRole.setId(dto.getUserRoleIdId());
//            user.setUserRoleId(userRole);
//        }
//        return user;
//    }

    public static StudentDto toDTO(User user) {
        if (user == null) {
            return null;
        }
        StudentDto dto = new StudentDto();
        dto.setId(user.getId());
        dto.setLastName(user.getLastName());
        dto.setFirstName(user.getFirstName());
        dto.setBirthday(user.getBirthday());
        dto.setUsername(user.getUsername());
        dto.setPassword(user.getPassword());
        dto.setAvatar(user.getAvatar());
        dto.setEmail(user.getEmail());
        if (user.getUserRoleId() != null) {
            dto.setUserRoleIdId(user.getUserRoleId().getId());
            dto.setUserRoleIdName(user.getUserRoleId().getName());
        }
        return dto;
    }
}
