package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.Question;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.WritingAnswer}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WritingAnswerDTO implements Serializable {
    private final Integer id;
    @NotNull(message = "Conntent cannot be null!")
    @Size(min = 1, max = 65535)
    private final String content;
    private final Question questionId;

    public WritingAnswerDTO(Integer id, String content, Question questionId) {
        this.id = id;
        this.content = content;
        this.questionId = questionId;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Question getQuestionId() {
        return questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WritingAnswerDTO entity = (WritingAnswerDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.content, entity.content) &&
                Objects.equals(this.questionId, entity.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, questionId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "content = " + content + ", " +
                "questionId = " + questionId + ")";
    }
}