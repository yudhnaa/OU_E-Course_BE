package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.Test;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.Test}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 100)
    private final String name;
    @Size(max = 65535)
    private final String description;
    private final int durationMinutes;
    private final LocalDateTime createdAt;
    @NotNull
    private final BigDecimal maxScore;
    private final Integer courseIdId;
    private final String courseIdName;
    private final String courseIdDescription;

    public TestDto(Integer id, String name, String description, int durationMinutes, LocalDateTime createdAt, BigDecimal maxScore, Integer courseIdId, String courseIdName, String courseIdDescription) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.createdAt = createdAt;
        this.maxScore = maxScore;
        this.courseIdId = courseIdId;
        this.courseIdName = courseIdName;
        this.courseIdDescription = courseIdDescription;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getMaxScore() {
        return maxScore;
    }

    public Integer getCourseIdId() {
        return courseIdId;
    }

    public String getCourseIdName() {
        return courseIdName;
    }

    public String getCourseIdDescription() {
        return courseIdDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestDto entity = (TestDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.durationMinutes, entity.durationMinutes) &&
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.maxScore, entity.maxScore) &&
                Objects.equals(this.courseIdId, entity.courseIdId) &&
                Objects.equals(this.courseIdName, entity.courseIdName) &&
                Objects.equals(this.courseIdDescription, entity.courseIdDescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, durationMinutes, createdAt, maxScore, courseIdId, courseIdName, courseIdDescription);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "durationMinutes = " + durationMinutes + ", " +
                "createdAt = " + createdAt + ", " +
                "maxScore = " + maxScore + ", " +
                "courseIdId = " + courseIdId + ", " +
                "courseIdName = " + courseIdName + ", " +
                "courseIdDescription = " + courseIdDescription + ")";
    }

    // Chuyen tu entity sang dto
    public static TestDto toDTO(Test test) {
        return new TestDto(
                test.getId(),
                test.getName(),
                test.getDescription(),
                test.getDurationMinutes(),
                test.getCreatedAt(),
                test.getMaxScore(),
                test.getCourseId() != null ? test.getCourseId().getId() : null,
                test.getCourseId() != null ? test.getCourseId().getName() : null,
                test.getCourseId() != null ? test.getCourseId().getDescription() : null
        );
    }
}