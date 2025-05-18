package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.WritingAnswer}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WritingAnswerDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 65535)
    private final String content;
    private final Integer questionIdId;
    private final String questionIdContent;

    public WritingAnswerDto(Integer id, String content, Integer questionIdId, String questionIdContent) {
        this.id = id;
        this.content = content;
        this.questionIdId = questionIdId;
        this.questionIdContent = questionIdContent;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
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
        WritingAnswerDto entity = (WritingAnswerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.content, entity.content) &&
                Objects.equals(this.questionIdId, entity.questionIdId) &&
                Objects.equals(this.questionIdContent, entity.questionIdContent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, questionIdId, questionIdContent);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "content = " + content + ", " +
                "questionIdId = " + questionIdId + ", " +
                "questionIdContent = " + questionIdContent + ")";
    }
}