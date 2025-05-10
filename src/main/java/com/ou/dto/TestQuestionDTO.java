package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.Question;
import com.ou.pojo.Test;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.TestQuestion}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestQuestionDTO implements Serializable {
    private final Integer id;
    private final Question questionId;
    private final Test testId;

    public TestQuestionDTO(Integer id, Question questionId, Test testId) {
        this.id = id;
        this.questionId = questionId;
        this.testId = testId;
    }

    public Integer getId() {
        return id;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public Test getTestId() {
        return testId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestQuestionDTO entity = (TestQuestionDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.questionId, entity.questionId) &&
                Objects.equals(this.testId, entity.testId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, testId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "questionId = " + questionId + ", " +
                "testId = " + testId + ")";
    }
}