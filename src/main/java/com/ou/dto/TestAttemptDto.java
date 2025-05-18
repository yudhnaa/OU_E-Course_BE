package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.TestAttempt}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestAttemptDto implements Serializable {
    private final Integer id;
    private final Date startedAt;
    private final Date submittedAt;
    private final BigDecimal totalScore;
    private final Integer testIdId;
    private final String testIdName;
    private final String testIdDescription;
    private final int testIdDurationMinutes;
    private final Date testIdCreatedAt;

    public TestAttemptDto(Integer id, Date startedAt, Date submittedAt, BigDecimal totalScore, Integer testIdId, String testIdName, String testIdDescription, int testIdDurationMinutes, Date testIdCreatedAt) {
        this.id = id;
        this.startedAt = startedAt;
        this.submittedAt = submittedAt;
        this.totalScore = totalScore;
        this.testIdId = testIdId;
        this.testIdName = testIdName;
        this.testIdDescription = testIdDescription;
        this.testIdDurationMinutes = testIdDurationMinutes;
        this.testIdCreatedAt = testIdCreatedAt;
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

    public Integer getTestIdId() {
        return testIdId;
    }

    public String getTestIdName() {
        return testIdName;
    }

    public String getTestIdDescription() {
        return testIdDescription;
    }

    public int getTestIdDurationMinutes() {
        return testIdDurationMinutes;
    }

    public Date getTestIdCreatedAt() {
        return testIdCreatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAttemptDto entity = (TestAttemptDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.startedAt, entity.startedAt) &&
                Objects.equals(this.submittedAt, entity.submittedAt) &&
                Objects.equals(this.totalScore, entity.totalScore) &&
                Objects.equals(this.testIdId, entity.testIdId) &&
                Objects.equals(this.testIdName, entity.testIdName) &&
                Objects.equals(this.testIdDescription, entity.testIdDescription) &&
                Objects.equals(this.testIdDurationMinutes, entity.testIdDurationMinutes) &&
                Objects.equals(this.testIdCreatedAt, entity.testIdCreatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startedAt, submittedAt, totalScore, testIdId, testIdName, testIdDescription, testIdDurationMinutes, testIdCreatedAt);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "startedAt = " + startedAt + ", " +
                "submittedAt = " + submittedAt + ", " +
                "totalScore = " + totalScore + ", " +
                "testIdId = " + testIdId + ", " +
                "testIdName = " + testIdName + ", " +
                "testIdDescription = " + testIdDescription + ", " +
                "testIdDurationMinutes = " + testIdDurationMinutes + ", " +
                "testIdCreatedAt = " + testIdCreatedAt + ")";
    }
}