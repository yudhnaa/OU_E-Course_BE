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
    private Integer id;
    @NotNull
    @Size(min = 1, max = 65535)
    private String content;
    private Set<WritingAnswerDto1> writingAnswerSet;
    private Set<MultipleChoiceAnswerDto1> multipleChoiceAnswerSet;

    public QuestionDto() {
    }

    public QuestionDto(Integer id, String content, Set<WritingAnswerDto1> writingAnswerSet, Set<MultipleChoiceAnswerDto1> multipleChoiceAnswerSet) {
        this.id = id;
        this.content = content;
        this.writingAnswerSet = writingAnswerSet;
        this.multipleChoiceAnswerSet = multipleChoiceAnswerSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Set<WritingAnswerDto1> getWritingAnswerSet() {
        return writingAnswerSet;
    }

    public void setWritingAnswerSet(Set<WritingAnswerDto1> writingAnswerSet) {
        this.writingAnswerSet = writingAnswerSet;
    }

    public Set<MultipleChoiceAnswerDto1> getMultipleChoiceAnswerSet() {
        return multipleChoiceAnswerSet;
    }

    public void setMultipleChoiceAnswerSet(Set<MultipleChoiceAnswerDto1> multipleChoiceAnswerSet) {
        this.multipleChoiceAnswerSet = multipleChoiceAnswerSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionDto entity = (QuestionDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.content, entity.content) &&
                Objects.equals(this.writingAnswerSet, entity.writingAnswerSet) &&
                Objects.equals(this.multipleChoiceAnswerSet, entity.multipleChoiceAnswerSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, writingAnswerSet, multipleChoiceAnswerSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "content = " + content + ", " +
                "writingAnswerSet = " + writingAnswerSet + ", " +
                "multipleChoiceAnswerSet = " + multipleChoiceAnswerSet + ")";
    }

    /**
     * DTO for {@link com.ou.pojo.WritingAnswer}
     */
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class WritingAnswerDto1 implements Serializable {
        private Integer id;
        private String content;

        public WritingAnswerDto1() {
        }

        public WritingAnswerDto1(Integer id, String content) {
            this.id = id;
            this.content = content;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            WritingAnswerDto1 entity = (WritingAnswerDto1) o;
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
        private Integer id;
        private String content;
        private boolean isCorrect;

        public MultipleChoiceAnswerDto1() {
        }

        public MultipleChoiceAnswerDto1(Integer id, String content, boolean isCorrect) {
            this.id = id;
            this.content = content;
            this.isCorrect = isCorrect;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public boolean getIsCorrect() {
            return isCorrect;
        }

        public void setIsCorrect(boolean isCorrect) {
            this.isCorrect = isCorrect;
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