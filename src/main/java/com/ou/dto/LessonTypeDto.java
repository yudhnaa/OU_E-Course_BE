package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.ou.pojo.LessonType}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonTypeDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private final String name;
    @Size(max = 65535)
    private final String description;
    private final Set<LessonDto> lessonSet;

    public LessonTypeDto(Integer id, String name, String description, Set<LessonDto> lessonSet) {
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

    public Set<LessonDto> getLessonSet() {
        return lessonSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonTypeDto entity = (LessonTypeDto) o;
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

    /**
     * DTO for {@link com.ou.pojo.Lesson}
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LessonDto implements Serializable {
        private final Integer id;
        private final String name;
        private final String embedLink;
        private final String description;

        public LessonDto(Integer id, String name, String embedLink, String description) {
            this.id = id;
            this.name = name;
            this.embedLink = embedLink;
            this.description = description;
        }

        public Integer getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getEmbedLink() {
            return embedLink;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            LessonDto entity = (LessonDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.name, entity.name) &&
                    Objects.equals(this.embedLink, entity.embedLink) &&
                    Objects.equals(this.description, entity.description);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name, embedLink, description);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "name = " + name + ", " +
                    "embedLink = " + embedLink + ", " +
                    "description = " + description + ")";
        }
    }
}