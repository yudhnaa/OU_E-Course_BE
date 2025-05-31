package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.TestAttemptAnswer;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO for {@link TestAttemptAnswer}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestAttemptAnswerDto implements Serializable {
    private Integer id;
    @Size(max = 65535)
    private String answerText;
    private Boolean isCorrect;
    private BigDecimal score;
    private Integer questionIdId;
    private Integer attemptIdId;

    public TestAttemptAnswerDto() {
    }

    public TestAttemptAnswerDto(Integer id, String answerText, Boolean isCorrect, BigDecimal score, Integer questionIdId, Integer attemptIdId) {
        this.id = id;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
        this.score = score;
        this.questionIdId = questionIdId;
        this.attemptIdId = attemptIdId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Integer getQuestionIdId() {
        return questionIdId;
    }

    public void setQuestionIdId(Integer questionIdId) {
        this.questionIdId = questionIdId;
    }

    public Integer getAttemptIdId() {
        return attemptIdId;
    }

    public void setAttemptIdId(Integer attemptIdId) {
        this.attemptIdId = attemptIdId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAttemptAnswerDto entity = (TestAttemptAnswerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.answerText, entity.answerText) &&
                Objects.equals(this.isCorrect, entity.isCorrect) &&
                Objects.equals(this.score, entity.score) &&
                Objects.equals(this.questionIdId, entity.questionIdId) &&
                Objects.equals(this.attemptIdId, entity.attemptIdId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answerText, isCorrect, score, questionIdId, attemptIdId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "answerText = " + answerText + ", " +
                "isCorrect = " + isCorrect + ", " +
                "score = " + score + ", " +
                "questionIdId = " + questionIdId + ", " +
                "attemptIdId = " + attemptIdId + ")";
    }
}