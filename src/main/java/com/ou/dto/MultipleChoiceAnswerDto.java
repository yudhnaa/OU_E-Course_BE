package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.MultipleChoiceAnswer}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MultipleChoiceAnswerDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 65535)
    private final String content;
    private final boolean isCorrect;
    private final Integer questionIdId;
    private final String questionIdContent;

    public MultipleChoiceAnswerDto(Integer id, String content, boolean isCorrect, Integer questionIdId, String questionIdContent) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
        this.questionIdId = questionIdId;
        this.questionIdContent = questionIdContent;
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

    public Integer getQuestionIdId() {
        return questionIdId;
    }

    public String getQuestionIdContent() {
        return questionIdContent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultipleChoiceAnswerDto entity = (MultipleChoiceAnswerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.content, entity.content) &&
                Objects.equals(this.isCorrect, entity.isCorrect) &&
                Objects.equals(this.questionIdId, entity.questionIdId) &&
                Objects.equals(this.questionIdContent, entity.questionIdContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, isCorrect, questionIdId, questionIdContent);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "content = " + content + ", " +
                "isCorrect = " + isCorrect + ", " +
                "questionIdId = " + questionIdId + ", " +
                "questionIdContent = " + questionIdContent + ")";
    }
}