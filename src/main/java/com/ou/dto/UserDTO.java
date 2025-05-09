package com.ou.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.User}
 */
public class UserDTO implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private final String lastName;
    @NotNull
    @Size(min = 1, max = 50)
    private final String firstName;
    @NotNull
    @Size(min = 1, max = 50)
    private final String birthday;
    @NotNull
    @Size(min = 1, max = 50)
    private final String username;
    @NotNull
    @Size(min = 1, max = 255)
    private final String avatar;
    @NotNull
    @Size(min = 1, max = 100)
    private final String email;
    private final boolean isActive;

    public UserDTO(Integer id, String lastName, String firstName, String birthday, String username, String avatar, String email, boolean isActive) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthday = birthday;
        this.username = username;
        this.avatar = avatar;
        this.email = email;
        this.isActive = isActive;
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getUsername() {
        return username;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getEmail() {
        return email;
    }

    public boolean getIsActive() {
        return isActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO entity = (UserDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.lastName, entity.lastName) &&
                Objects.equals(this.firstName, entity.firstName) &&
                Objects.equals(this.birthday, entity.birthday) &&
                Objects.equals(this.username, entity.username) &&
                Objects.equals(this.avatar, entity.avatar) &&
                Objects.equals(this.email, entity.email) &&
                Objects.equals(this.isActive, entity.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName, birthday, username, avatar, email, isActive);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "lastName = " + lastName + ", " +
                "firstName = " + firstName + ", " +
                "birthday = " + birthday + ", " +
                "username = " + username + ", " +
                "avatar = " + avatar + ", " +
                "email = " + email + ", " +
                "isActive = " + isActive + ")";
    }
}