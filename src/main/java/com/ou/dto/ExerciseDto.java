package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.Exercise;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link Exercise}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    private int durationMinutes;
    private Integer courseIdId;
    private String courseIdName;
    private Integer lessonIdId;
    private String lessonIdName;
    private Integer createdByUserIdId;
    private String createdByUserIdUsername;

    public ExerciseDto() {
    }

    public ExerciseDto(Integer id, String name, int durationMinutes, Integer courseIdId, String courseIdName, Integer lessonIdId, String lessonIdName, Integer createdByUserIdId, String createdByUserIdUsername) {
        this.id = id;
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.courseIdId = courseIdId;
        this.courseIdName = courseIdName;
        this.lessonIdId = lessonIdId;
        this.lessonIdName = lessonIdName;
        this.createdByUserIdId = createdByUserIdId;
        this.createdByUserIdUsername = createdByUserIdUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseDto entity = (ExerciseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.durationMinutes, entity.durationMinutes) &&
                Objects.equals(this.courseIdId, entity.courseIdId) &&
                Objects.equals(this.courseIdName, entity.courseIdName) &&
                Objects.equals(this.lessonIdId, entity.lessonIdId) &&
                Objects.equals(this.lessonIdName, entity.lessonIdName) &&
                Objects.equals(this.createdByUserIdId, entity.createdByUserIdId) &&
                Objects.equals(this.createdByUserIdUsername, entity.createdByUserIdUsername);
    }

    public Integer getCourseIdId() {
        return courseIdId;
    }

    public String getCourseIdName() {
        return courseIdName;
    }

    public Integer getCreatedByUserIdId() {
        return createdByUserIdId;
    }

    public String getCreatedByUserIdUsername() {
        return createdByUserIdUsername;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public Integer getId() {
        return id;
    }

    public Integer getLessonIdId() {
        return lessonIdId;
    }

    public String getLessonIdName() {
        return lessonIdName;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, durationMinutes, courseIdId, courseIdName, lessonIdId, lessonIdName, createdByUserIdId, createdByUserIdUsername);
    }

    public void setCourseIdId(Integer courseIdId) {
        this.courseIdId = courseIdId;
    }

    public void setCourseIdName(String courseIdName) {
        this.courseIdName = courseIdName;
    }

    public void setCreatedByUserIdId(Integer createdByUserIdId) {
        this.createdByUserIdId = createdByUserIdId;
    }

    public void setCreatedByUserIdUsername(String createdByUserIdUsername) {
        this.createdByUserIdUsername = createdByUserIdUsername;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLessonIdId(Integer lessonIdId) {
        this.lessonIdId = lessonIdId;
    }

    public void setLessonIdName(String lessonIdName) {
        this.lessonIdName = lessonIdName;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "durationMinutes = " + durationMinutes + ", " +
                "courseIdId = " + courseIdId + ", " +
                "courseIdName = " + courseIdName + ", " +
                "lessonIdId = " + lessonIdId + ", " +
                "lessonIdName = " + lessonIdName + ", " +
                "createdByUserIdId = " + createdByUserIdId + ", " +
                "createdByUserIdUsername = " + createdByUserIdUsername + ")";
    }
}