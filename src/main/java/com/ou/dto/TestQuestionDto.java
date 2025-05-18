package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.TestQuestion}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestQuestionDto implements Serializable {
    private final Integer id;
    private final Integer questionIdId;
    private final String questionIdContent;
    private final Integer testIdId;
    private final String testIdName;
    private final String testIdDescription;
    private final int testIdDurationMinutes;
    private final Date testIdCreatedAt;

    public TestQuestionDto(Integer id, Integer questionIdId, String questionIdContent, Integer testIdId, String testIdName, String testIdDescription, int testIdDurationMinutes, Date testIdCreatedAt) {
        this.id = id;
        this.questionIdId = questionIdId;
        this.questionIdContent = questionIdContent;
        this.testIdId = testIdId;
        this.testIdName = testIdName;
        this.testIdDescription = testIdDescription;
        this.testIdDurationMinutes = testIdDurationMinutes;
        this.testIdCreatedAt = testIdCreatedAt;
    }

    public Integer getId() {
        return id;
    }

    public Integer getQuestionIdId() {
        return questionIdId;
    }

    public String getQuestionIdContent() {
        return questionIdContent;
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
        TestQuestionDto entity = (TestQuestionDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.questionIdId, entity.questionIdId) &&
                Objects.equals(this.questionIdContent, entity.questionIdContent) &&
                Objects.equals(this.testIdId, entity.testIdId) &&
                Objects.equals(this.testIdName, entity.testIdName) &&
                Objects.equals(this.testIdDescription, entity.testIdDescription) &&
                Objects.equals(this.testIdDurationMinutes, entity.testIdDurationMinutes) &&
                Objects.equals(this.testIdCreatedAt, entity.testIdCreatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionIdId, questionIdContent, testIdId, testIdName, testIdDescription, testIdDurationMinutes, testIdCreatedAt);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "questionIdId = " + questionIdId + ", " +
                "questionIdContent = " + questionIdContent + ", " +
                "testIdId = " + testIdId + ", " +
                "testIdName = " + testIdName + ", " +
                "testIdDescription = " + testIdDescription + ", " +
                "testIdDurationMinutes = " + testIdDurationMinutes + ", " +
                "testIdCreatedAt = " + testIdCreatedAt + ")";
    }
}