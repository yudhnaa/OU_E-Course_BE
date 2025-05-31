package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private Integer id;
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    @Size(max = 65535)
    private String description;
    private int durationMinutes;
    private LocalDateTime createdAt;
    @NotNull
    private BigDecimal maxScore;
    private Integer courseIdId;

    public TestDto() {
    }

    public TestDto(Integer id, String name, String description, int durationMinutes, LocalDateTime createdAt, BigDecimal maxScore, Integer courseIdId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.createdAt = createdAt;
        this.maxScore = maxScore;
        this.courseIdId = courseIdId;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
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
                Objects.equals(this.courseIdId, entity.courseIdId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, durationMinutes, createdAt, maxScore, courseIdId);
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
                "courseIdId = " + courseIdId + ")";
    }
}