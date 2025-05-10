package com.ou.dto;

import com.ou.pojo.Lesson;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.ou.pojo.LessonType}
 */
public class LessonTypeDTO implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private final String name;
    @Size(max = 65535)
    private final String description;
    private final Set<Lesson> lessonSet;

    public LessonTypeDTO(Integer id, String name, String description, Set<Lesson> lessonSet) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.lessonSet = lessonSet;
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

    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonTypeDTO entity = (LessonTypeDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.lessonSet, entity.lessonSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, lessonSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "lessonSet = " + lessonSet + ")";
    }
}