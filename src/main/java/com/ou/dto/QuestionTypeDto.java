package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.ou.pojo.QuestionType}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionTypeDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private final String name;
    @Size(max = 65535)
    private final String description;
    private final Set<QuestionDto1> questionSet;

    public QuestionTypeDto(Integer id, String name, String description, Set<QuestionDto1> questionSet) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.questionSet = questionSet;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<QuestionDto1> getQuestionSet() {
        return questionSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionTypeDto entity = (QuestionTypeDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.questionSet, entity.questionSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, questionSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "description = " + description + ", " +
                "questionSet = " + questionSet + ")";
    }

    /**
     * DTO for {@link com.ou.pojo.Question}
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class QuestionDto1 implements Serializable {
        private final Integer id;
        private final String content;

        public QuestionDto1(Integer id, String content) {
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
            QuestionDto1 entity = (QuestionDto1) o;
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
}