package com.ou.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.WritingAnswer}
 */
public class WritingAnswerDTO implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 65535)
    private final String content;

    public WritingAnswerDTO(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WritingAnswerDTO entity = (WritingAnswerDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.content, entity.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "content = " + content + ")";
    }
}