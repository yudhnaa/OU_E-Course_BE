package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.Test;
import com.ou.pojo.User;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.TestAttempt}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestAttemptDTO implements Serializable {
    private final Integer id;
    private final Date startedAt;
    private final Date submittedAt;
    private final BigDecimal totalScore;
    private final Test testId;
    private final User userId;

    public TestAttemptDTO(Integer id, Date startedAt, Date submittedAt, BigDecimal totalScore, Test testId, User userId) {
        this.id = id;
        this.startedAt = startedAt;
        this.submittedAt = submittedAt;
        this.totalScore = totalScore;
        this.testId = testId;
        this.userId = userId;
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

    public Test getTestId() {
        return testId;
    }

    public User getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAttemptDTO entity = (TestAttemptDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.startedAt, entity.startedAt) &&
                Objects.equals(this.submittedAt, entity.submittedAt) &&
                Objects.equals(this.totalScore, entity.totalScore) &&
                Objects.equals(this.testId, entity.testId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startedAt, submittedAt, totalScore, testId, userId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "startedAt = " + startedAt + ", " +
                "submittedAt = " + submittedAt + ", " +
                "totalScore = " + totalScore + ", " +
                "testId = " + testId + ", " +
                "userId = " + userId + ")";
    }
}