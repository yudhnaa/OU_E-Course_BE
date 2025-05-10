package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.ou.pojo.Test}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestDTO implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 100)
    private final String name;
    @Size(max = 65535)
    private final String description;
    private final int durationMinutes;
    private final Date createdAt;
    private final Set<TestQuestion> testQuestionSet;
    private final Course courseId;
    private final Lesson lessonId;
    private final User createdByUserId;
    private final Set<TestAttempt> testAttemptSet;

    public TestDTO(Integer id, String name, String description, int durationMinutes, Date createdAt, Set<TestQuestion> testQuestionSet, Course courseId, Lesson lessonId, User createdByUserId, Set<TestAttempt> testAttemptSet) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.createdAt = createdAt;
        this.testQuestionSet = testQuestionSet;
        this.courseId = courseId;
        this.lessonId = lessonId;
        this.createdByUserId = createdByUserId;
        this.testAttemptSet = testAttemptSet;
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

    public Set<TestQuestion> getTestQuestionSet() {
        return testQuestionSet;
    }

    public Course getCourseId() {
        return courseId;
    }

    public Lesson getLessonId() {
        return lessonId;
    }

    public User getCreatedByUserId() {
        return createdByUserId;
    }

    public Set<TestAttempt> getTestAttemptSet() {
        return testAttemptSet;
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
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.testQuestionSet, entity.testQuestionSet) &&
                Objects.equals(this.courseId, entity.courseId) &&
                Objects.equals(this.lessonId, entity.lessonId) &&
                Objects.equals(this.createdByUserId, entity.createdByUserId) &&
                Objects.equals(this.testAttemptSet, entity.testAttemptSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, durationMinutes, createdAt, testQuestionSet, courseId, lessonId, createdByUserId, testAttemptSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "durationMinutes = " + durationMinutes + ", " +
                "createdAt = " + createdAt + ", " +
                "testQuestionSet = " + testQuestionSet + ", " +
                "courseId = " + courseId + ", " +
                "lessonId = " + lessonId + ", " +
                "createdByUserId = " + createdByUserId + ", " +
                "testAttemptSet = " + testAttemptSet + ")";
    }
}