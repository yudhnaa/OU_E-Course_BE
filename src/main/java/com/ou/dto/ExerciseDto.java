package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.Exercise;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
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
    @NotNull
    private BigDecimal maxScore;
    private Integer courseIdId;
    private Integer lessonIdId;
    private String lessonIdName;
    private int lessonIdOrderIndex;

    public ExerciseDto() {
    }

    public ExerciseDto(Integer id, String name, int durationMinutes, BigDecimal maxScore, Integer courseIdId, Integer lessonIdId, String lessonIdName, int lessonIdOrderIndex) {
        this.id = id;
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.maxScore = maxScore;
        this.courseIdId = courseIdId;
        this.lessonIdId = lessonIdId;
        this.lessonIdName = lessonIdName;
        this.lessonIdOrderIndex = lessonIdOrderIndex;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public BigDecimal getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(BigDecimal maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getCourseIdId() {
        return courseIdId;
    }

    public void setCourseIdId(Integer courseIdId) {
        this.courseIdId = courseIdId;
    }

    public Integer getLessonIdId() {
        return lessonIdId;
    }

    public void setLessonIdId(Integer lessonIdId) {
        this.lessonIdId = lessonIdId;
    }

    public String getLessonIdName() {
        return lessonIdName;
    }

    public void setLessonIdName(String lessonIdName) {
        this.lessonIdName = lessonIdName;
    }

    public int getLessonIdOrderIndex() {
        return lessonIdOrderIndex;
    }

    public void setLessonIdOrderIndex(int lessonIdOrderIndex) {
        this.lessonIdOrderIndex = lessonIdOrderIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseDto entity = (ExerciseDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.durationMinutes, entity.durationMinutes) &&
                Objects.equals(this.maxScore, entity.maxScore) &&
                Objects.equals(this.courseIdId, entity.courseIdId) &&
                Objects.equals(this.lessonIdId, entity.lessonIdId) &&
                Objects.equals(this.lessonIdName, entity.lessonIdName) &&
                Objects.equals(this.lessonIdOrderIndex, entity.lessonIdOrderIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, durationMinutes, maxScore, courseIdId, lessonIdId, lessonIdName, lessonIdOrderIndex);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "durationMinutes = " + durationMinutes + ", " +
                "maxScore = " + maxScore + ", " +
                "courseIdId = " + courseIdId + ", " +
                "lessonIdId = " + lessonIdId + ", " +
                "lessonIdName = " + lessonIdName + ", " +
                "lessonIdOrderIndex = " + lessonIdOrderIndex + ")";
    }
}