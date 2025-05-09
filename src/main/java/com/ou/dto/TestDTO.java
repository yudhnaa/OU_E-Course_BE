package com.ou.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.Test}
 */
public class TestDTO implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 100)
    private final String name;
    @Size(max = 65535)
    private final String description;
    private final int durationMinutes;
    private final Date createdAt;

    public TestDTO(Integer id, String name, String description, int durationMinutes, Date createdAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.createdAt = createdAt;
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

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestDTO entity = (TestDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.durationMinutes, entity.durationMinutes) &&
                Objects.equals(this.createdAt, entity.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, durationMinutes, createdAt);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "durationMinutes = " + durationMinutes + ", " +
                "createdAt = " + createdAt + ")";
    }
}