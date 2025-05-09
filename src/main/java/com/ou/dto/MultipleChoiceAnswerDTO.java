package com.ou.dto;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.MultipleChoiceAnswer}
 */
public class MultipleChoiceAnswerDTO implements Serializable {
    @NotNull
    @Positive
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 65535)
    @NotEmpty(message = "Content can not be empty")
    @NotBlank(message = "Content can not be blank")
    private final String content;
    private final boolean isCorrect;

    public MultipleChoiceAnswerDTO(Integer id, String content, boolean isCorrect) {
        this.id = id;
        this.content = content;
        this.isCorrect = isCorrect;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MultipleChoiceAnswerDTO entity = (MultipleChoiceAnswerDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.content, entity.content) &&
                Objects.equals(this.isCorrect, entity.isCorrect);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, isCorrect);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "content = " + content + ", " +
                "isCorrect = " + isCorrect + ")";
    }
}