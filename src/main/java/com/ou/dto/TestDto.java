package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

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
    private final Date createdAt;
    private final Set<Integer> testQuestionSetIds;
    private final Integer courseIdId;
    private final String courseIdName;
    private final String courseIdDescription;
    private final Date courseIdDateAdded;
    private final Date courseIdDateStart;
    private final Date courseIdDateEnd;
    private final Integer lessonIdId;
    private final String lessonIdName;
    private final String lessonIdEmbedLink;
    private final String lessonIdDescription;
    private final Set<TestAttemptDto> testAttemptSet;

    public TestDto(Integer id, String name, String description, int durationMinutes, Date createdAt, Set<Integer> testQuestionSetIds, Integer courseIdId, String courseIdName, String courseIdDescription, Date courseIdDateAdded, Date courseIdDateStart, Date courseIdDateEnd, Integer lessonIdId, String lessonIdName, String lessonIdEmbedLink, String lessonIdDescription, Set<TestAttemptDto> testAttemptSet) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.durationMinutes = durationMinutes;
        this.createdAt = createdAt;
        this.testQuestionSetIds = testQuestionSetIds;
        this.courseIdId = courseIdId;
        this.courseIdName = courseIdName;
        this.courseIdDescription = courseIdDescription;
        this.courseIdDateAdded = courseIdDateAdded;
        this.courseIdDateStart = courseIdDateStart;
        this.courseIdDateEnd = courseIdDateEnd;
        this.lessonIdId = lessonIdId;
        this.lessonIdName = lessonIdName;
        this.lessonIdEmbedLink = lessonIdEmbedLink;
        this.lessonIdDescription = lessonIdDescription;
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

    public Set<Integer> getTestQuestionSetIds() {
        return testQuestionSetIds;
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

    public Date getCourseIdDateAdded() {
        return courseIdDateAdded;
    }

    public Date getCourseIdDateStart() {
        return courseIdDateStart;
    }

    public Date getCourseIdDateEnd() {
        return courseIdDateEnd;
    }

    public Integer getLessonIdId() {
        return lessonIdId;
    }

    public String getLessonIdName() {
        return lessonIdName;
    }

    public String getLessonIdEmbedLink() {
        return lessonIdEmbedLink;
    }

    public String getLessonIdDescription() {
        return lessonIdDescription;
    }

    public Set<TestAttemptDto> getTestAttemptSet() {
        return testAttemptSet;
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
                Objects.equals(this.testQuestionSetIds, entity.testQuestionSetIds) &&
                Objects.equals(this.courseIdId, entity.courseIdId) &&
                Objects.equals(this.courseIdName, entity.courseIdName) &&
                Objects.equals(this.courseIdDescription, entity.courseIdDescription) &&
                Objects.equals(this.courseIdDateAdded, entity.courseIdDateAdded) &&
                Objects.equals(this.courseIdDateStart, entity.courseIdDateStart) &&
                Objects.equals(this.courseIdDateEnd, entity.courseIdDateEnd) &&
                Objects.equals(this.lessonIdId, entity.lessonIdId) &&
                Objects.equals(this.lessonIdName, entity.lessonIdName) &&
                Objects.equals(this.lessonIdEmbedLink, entity.lessonIdEmbedLink) &&
                Objects.equals(this.lessonIdDescription, entity.lessonIdDescription) &&
                Objects.equals(this.testAttemptSet, entity.testAttemptSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, durationMinutes, createdAt, testQuestionSetIds, courseIdId, courseIdName, courseIdDescription, courseIdDateAdded, courseIdDateStart, courseIdDateEnd, lessonIdId, lessonIdName, lessonIdEmbedLink, lessonIdDescription, testAttemptSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "durationMinutes = " + durationMinutes + ", " +
                "createdAt = " + createdAt + ", " +
                "testQuestionSetIds = " + testQuestionSetIds + ", " +
                "courseIdId = " + courseIdId + ", " +
                "courseIdName = " + courseIdName + ", " +
                "courseIdDescription = " + courseIdDescription + ", " +
                "courseIdDateAdded = " + courseIdDateAdded + ", " +
                "courseIdDateStart = " + courseIdDateStart + ", " +
                "courseIdDateEnd = " + courseIdDateEnd + ", " +
                "lessonIdId = " + lessonIdId + ", " +
                "lessonIdName = " + lessonIdName + ", " +
                "lessonIdEmbedLink = " + lessonIdEmbedLink + ", " +
                "lessonIdDescription = " + lessonIdDescription + ", " +
                "testAttemptSet = " + testAttemptSet + ")";
    }

    /**
     * DTO for {@link com.ou.pojo.TestAttempt}
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class TestAttemptDto implements Serializable {
        private final Integer id;
        private final Date startedAt;
        private final Date submittedAt;
        private final BigDecimal totalScore;

        public TestAttemptDto(Integer id, Date startedAt, Date submittedAt, BigDecimal totalScore) {
            this.id = id;
            this.startedAt = startedAt;
            this.submittedAt = submittedAt;
            this.totalScore = totalScore;
        }

        public Integer getId() {
            return id;
        }

        public Date getStartedAt() {
            return startedAt;
        }

        public Date getSubmittedAt() {
            return submittedAt;
        }

        public BigDecimal getTotalScore() {
            return totalScore;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TestAttemptDto entity = (TestAttemptDto) o;
            return Objects.equals(this.id, entity.id) &&
                    Objects.equals(this.startedAt, entity.startedAt) &&
                    Objects.equals(this.submittedAt, entity.submittedAt) &&
                    Objects.equals(this.totalScore, entity.totalScore);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, startedAt, submittedAt, totalScore);
        }

        @Override
        public String toString() {
            return getClass().getSimpleName() + "(" +
                    "id = " + id + ", " +
                    "startedAt = " + startedAt + ", " +
                    "submittedAt = " + submittedAt + ", " +
                    "totalScore = " + totalScore + ")";
        }
    }
}