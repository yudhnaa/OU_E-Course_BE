package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.ou.pojo.UserRole}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRoleDTO implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    @NotEmpty(message = "Name of the role cannot be empty!")
    @NotBlank(message = "Name of the role cannot be empty!")
    private final String name;
    @Size(max = 65535)
    private final String description;
    private final Set<User> userSet;

    public UserRoleDTO(Integer id, String name, String description, Set<User> userSet) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userSet = userSet;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRoleDTO entity = (UserRoleDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.userSet, entity.userSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, userSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "userSet = " + userSet + ")";
    }
}