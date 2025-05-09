package com.ou.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.LessonStudent}
 */
public class LessonStudentDTO implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private final String name;
    @NotNull
    private final Boolean isLearn;

    public LessonStudentDTO(Integer id, String name, Boolean isLearn) {
        this.id = id;
        this.name = name;
        this.isLearn = isLearn;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonStudentDTO entity = (LessonStudentDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.isLearn, entity.isLearn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isLearn);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "isLearn = " + isLearn + ")";
    }
}