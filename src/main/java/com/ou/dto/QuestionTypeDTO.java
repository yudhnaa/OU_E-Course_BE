package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.Question;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.ou.pojo.QuestionType}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionTypeDTO implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private final String name;
    @Size(max = 65535)
    private final String description;
    private final Set<Question> questionSet;

    public QuestionTypeDTO(Integer id, String name, String description, Set<Question> questionSet) {
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

    public Set<Question> getQuestionSet() {
        return questionSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionTypeDTO entity = (QuestionTypeDTO) o;
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
}