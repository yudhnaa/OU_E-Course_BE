package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.ExerciseAttemptAnswer}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseAttemptAnswerDto implements Serializable {
    private Integer id;
    @Size(max = 65535)
    private String answerText;
    private Boolean isCorrect;
    private BigDecimal score;
    private Integer attemptIdId;
    private Integer questionIdId;

    public ExerciseAttemptAnswerDto() {
    }

    public ExerciseAttemptAnswerDto(Integer id, String answerText, Boolean isCorrect, BigDecimal score, Integer attemptIdId, Integer questionIdId) {
        this.id = id;
        this.answerText = answerText;
        this.isCorrect = isCorrect;
        this.score = score;
        this.attemptIdId = attemptIdId;
        this.questionIdId = questionIdId;
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

    public Integer getAttemptIdId() {
        return attemptIdId;
    }

    public void setAttemptIdId(Integer attemptIdId) {
        this.attemptIdId = attemptIdId;
    }

    public Integer getQuestionIdId() {
        return questionIdId;
    }

    public void setQuestionIdId(Integer questionIdId) {
        this.questionIdId = questionIdId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseAttemptAnswerDto entity = (ExerciseAttemptAnswerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.answerText, entity.answerText) &&
                Objects.equals(this.isCorrect, entity.isCorrect) &&
                Objects.equals(this.score, entity.score) &&
                Objects.equals(this.attemptIdId, entity.attemptIdId) &&
                Objects.equals(this.questionIdId, entity.questionIdId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, answerText, isCorrect, score, attemptIdId, questionIdId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "answerText = " + answerText + ", " +
                "isCorrect = " + isCorrect + ", " +
                "score = " + score + ", " +
                "attemptIdId = " + attemptIdId + ", " +
                "questionIdId = " + questionIdId + ")";
    }
}