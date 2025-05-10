package com.ou.dto;

import com.ou.pojo.Question;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.MultipleChoiceAnswer}
 */
public class MultipleChoiceAnswerDTO implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 65535)
    private final String content;
    private final boolean isCorrect;
    private final Question questionId;

    public MultipleChoiceAnswerDTO(Integer id, String content, boolean isCorrect, Question questionId) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.questionId = questionId;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public boolean getIsCorrect() {
        return isCorrect;
    }

    public Question getQuestionId() {
        return questionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultipleChoiceAnswerDTO entity = (MultipleChoiceAnswerDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.content, entity.content) &&
                Objects.equals(this.isCorrect, entity.isCorrect) &&
                Objects.equals(this.questionId, entity.questionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, isCorrect, questionId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "content = " + content + ", " +
                "isCorrect = " + isCorrect + ", " +
                "questionId = " + questionId + ")";
    }
}