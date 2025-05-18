package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.ou.pojo.Question}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QuestionDto implements Serializable {
    private final Integer id;
    @NotNull
    @Size(min = 1, max = 65535)
    private final String content;
    private final Set<WritingAnswerDto> writingAnswerSet;
    private final Set<Integer> testQuestionSetIds;
    private final Integer exerciseIdId;
    private final String exerciseIdName;
    private final int exerciseIdDurationMinutes;
    private final Integer questionTypeIdId;
    private final String questionTypeIdName;
    private final String questionTypeIdDescription;
    private final Set<MultipleChoiceAnswerDto1> multipleChoiceAnswerSet;

    public QuestionDto(Integer id, String content, Set<WritingAnswerDto> writingAnswerSet, Set<Integer> testQuestionSetIds, Integer exerciseIdId, String exerciseIdName, int exerciseIdDurationMinutes, Integer questionTypeIdId, String questionTypeIdName, String questionTypeIdDescription, Set<MultipleChoiceAnswerDto1> multipleChoiceAnswerSet) {
        this.id = id;
        this.content = content;
        this.writingAnswerSet = writingAnswerSet;
        this.testQuestionSetIds = testQuestionSetIds;
        this.exerciseIdId = exerciseIdId;
        this.exerciseIdName = exerciseIdName;
        this.exerciseIdDurationMinutes = exerciseIdDurationMinutes;
        this.questionTypeIdId = questionTypeIdId;
        this.questionTypeIdName = questionTypeIdName;
        this.questionTypeIdDescription = questionTypeIdDescription;
        this.multipleChoiceAnswerSet = multipleChoiceAnswerSet;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Set<WritingAnswerDto> getWritingAnswerSet() {
        return writingAnswerSet;
    }

    public Set<Integer> getTestQuestionSetIds() {
        return testQuestionSetIds;
    }

    public Integer getExerciseIdId() {
        return exerciseIdId;
    }

    public String getExerciseIdName() {
        return exerciseIdName;
    }

    public int getExerciseIdDurationMinutes() {
        return exerciseIdDurationMinutes;
    }

    public Integer getQuestionTypeIdId() {
        return questionTypeIdId;
    }

    public String getQuestionTypeIdName() {
        return questionTypeIdName;
    }

    public String getQuestionTypeIdDescription() {
        return questionTypeIdDescription;
    }

    public Set<MultipleChoiceAnswerDto1> getMultipleChoiceAnswerSet() {
        return multipleChoiceAnswerSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto entity = (QuestionDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.content, entity.content) &&
                Objects.equals(this.writingAnswerSet, entity.writingAnswerSet) &&
                Objects.equals(this.testQuestionSetIds, entity.testQuestionSetIds) &&
                Objects.equals(this.exerciseIdId, entity.exerciseIdId) &&
                Objects.equals(this.exerciseIdName, entity.exerciseIdName) &&
                Objects.equals(this.exerciseIdDurationMinutes, entity.exerciseIdDurationMinutes) &&
                Objects.equals(this.questionTypeIdId, entity.questionTypeIdId) &&
                Objects.equals(this.questionTypeIdName, entity.questionTypeIdName) &&
                Objects.equals(this.questionTypeIdDescription, entity.questionTypeIdDescription) &&
                Objects.equals(this.multipleChoiceAnswerSet, entity.multipleChoiceAnswerSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, writingAnswerSet, testQuestionSetIds, exerciseIdId, exerciseIdName, exerciseIdDurationMinutes, questionTypeIdId, questionTypeIdName, questionTypeIdDescription, multipleChoiceAnswerSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "content = " + content + ", " +
                "writingAnswerSet = " + writingAnswerSet + ", " +
                "testQuestionSetIds = " + testQuestionSetIds + ", " +
                "exerciseIdId = " + exerciseIdId + ", " +
                "exerciseIdName = " + exerciseIdName + ", " +
                "exerciseIdDurationMinutes = " + exerciseIdDurationMinutes + ", " +
                "questionTypeIdId = " + questionTypeIdId + ", " +
                "questionTypeIdName = " + questionTypeIdName + ", " +
                "questionTypeIdDescription = " + questionTypeIdDescription + ", " +
                "multipleChoiceAnswerSet = " + multipleChoiceAnswerSet + ")";
    }

    /**
     * DTO for {@link com.ou.pojo.WritingAnswer}
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WritingAnswerDto implements Serializable {
        private final Integer id;
        private final String content;

        public WritingAnswerDto(Integer id, String content) {
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
            WritingAnswerDto entity = (WritingAnswerDto) o;
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

    /**
     * DTO for {@link com.ou.pojo.MultipleChoiceAnswer}
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MultipleChoiceAnswerDto1 implements Serializable {
        private final Integer id;
        private final String content;
        private final boolean isCorrect;

        public MultipleChoiceAnswerDto1(Integer id, String content, boolean isCorrect) {
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
            MultipleChoiceAnswerDto1 entity = (MultipleChoiceAnswerDto1) o;
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
}