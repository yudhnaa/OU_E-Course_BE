package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.ou.pojo.Question}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionDTO implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 65535)
    private final String content;
    private final Set<WritingAnswer> writingAnswerSet;
    private final Set<TestQuestion> testQuestionSet;
    private final Exercise exerciseId;
    private final QuestionType questionTypeId;
    private final Set<MultipleChoiceAnswer> multipleChoiceAnswerSet;

    public QuestionDTO(Integer id, String content, Set<WritingAnswer> writingAnswerSet, Set<TestQuestion> testQuestionSet, Exercise exerciseId, QuestionType questionTypeId, Set<MultipleChoiceAnswer> multipleChoiceAnswerSet) {
        this.id = id;
        this.content = content;
        this.writingAnswerSet = writingAnswerSet;
        this.testQuestionSet = testQuestionSet;
        this.exerciseId = exerciseId;
        this.questionTypeId = questionTypeId;
        this.multipleChoiceAnswerSet = multipleChoiceAnswerSet;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Set<WritingAnswer> getWritingAnswerSet() {
        return writingAnswerSet;
    }

    public Set<TestQuestion> getTestQuestionSet() {
        return testQuestionSet;
    }

    public Exercise getExerciseId() {
        return exerciseId;
    }

    public QuestionType getQuestionTypeId() {
        return questionTypeId;
    }

    public Set<MultipleChoiceAnswer> getMultipleChoiceAnswerSet() {
        return multipleChoiceAnswerSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDTO entity = (QuestionDTO) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.content, entity.content) &&
                Objects.equals(this.writingAnswerSet, entity.writingAnswerSet) &&
                Objects.equals(this.testQuestionSet, entity.testQuestionSet) &&
                Objects.equals(this.exerciseId, entity.exerciseId) &&
                Objects.equals(this.questionTypeId, entity.questionTypeId) &&
                Objects.equals(this.multipleChoiceAnswerSet, entity.multipleChoiceAnswerSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, writingAnswerSet, testQuestionSet, exerciseId, questionTypeId, multipleChoiceAnswerSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "content = " + content + ", " +
                "writingAnswerSet = " + writingAnswerSet + ", " +
                "testQuestionSet = " + testQuestionSet + ", " +
                "exerciseId = " + exerciseId + ", " +
                "questionTypeId = " + questionTypeId + ", " +
                "multipleChoiceAnswerSet = " + multipleChoiceAnswerSet + ")";
    }
}