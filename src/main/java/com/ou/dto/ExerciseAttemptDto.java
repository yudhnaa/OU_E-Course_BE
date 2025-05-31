package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.ou.pojo.ExerciseAttempt}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseAttemptDto implements Serializable {
    private Integer id;
    private LocalDateTime startedAt;
    private LocalDateTime submittedAt;
    private BigDecimal totalScore;
    @Size(max = 65535)
    private String response;
    private Integer exerciseIdId;
    private Integer statusIdId;
    private String statusIdName;
    private Integer studentIdId;
    private Set<ExerciseAttemptAnswerDto> exerciseAttemptAnswerSet;

    public ExerciseAttemptDto() {
    }

    public ExerciseAttemptDto(Integer id, LocalDateTime startedAt, LocalDateTime submittedAt, BigDecimal totalScore, String response, Integer exerciseIdId, Integer statusIdId, String statusIdName, Integer studentIdId, Set<ExerciseAttemptAnswerDto> exerciseAttemptAnswerSet) {
        this.id = id;
        this.startedAt = startedAt;
        this.submittedAt = submittedAt;
        this.totalScore = totalScore;
        this.response = response;
        this.exerciseIdId = exerciseIdId;
        this.statusIdId = statusIdId;
        this.statusIdName = statusIdName;
        this.studentIdId = studentIdId;
        this.exerciseAttemptAnswerSet = exerciseAttemptAnswerSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(LocalDateTime startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDateTime getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(LocalDateTime submittedAt) {
        this.submittedAt = submittedAt;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Integer getExerciseIdId() {
        return exerciseIdId;
    }

    public void setExerciseIdId(Integer exerciseIdId) {
        this.exerciseIdId = exerciseIdId;
    }

    public Integer getStatusIdId() {
        return statusIdId;
    }

    public void setStatusIdId(Integer statusIdId) {
        this.statusIdId = statusIdId;
    }

    public String getStatusIdName() {
        return statusIdName;
    }

    public void setStatusIdName(String statusIdName) {
        this.statusIdName = statusIdName;
    }

    public Integer getStudentIdId() {
        return studentIdId;
    }

    public void setStudentIdId(Integer studentIdId) {
        this.studentIdId = studentIdId;
    }

    public Set<ExerciseAttemptAnswerDto> getExerciseAttemptAnswerSet() {
        return exerciseAttemptAnswerSet;
    }

    public void setExerciseAttemptAnswerSet(Set<ExerciseAttemptAnswerDto> exerciseAttemptAnswerSet) {
        this.exerciseAttemptAnswerSet = exerciseAttemptAnswerSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExerciseAttemptDto entity = (ExerciseAttemptDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.startedAt, entity.startedAt) &&
                Objects.equals(this.submittedAt, entity.submittedAt) &&
                Objects.equals(this.totalScore, entity.totalScore) &&
                Objects.equals(this.response, entity.response) &&
                Objects.equals(this.exerciseIdId, entity.exerciseIdId) &&
                Objects.equals(this.statusIdId, entity.statusIdId) &&
                Objects.equals(this.statusIdName, entity.statusIdName) &&
                Objects.equals(this.studentIdId, entity.studentIdId) &&
                Objects.equals(this.exerciseAttemptAnswerSet, entity.exerciseAttemptAnswerSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startedAt, submittedAt, totalScore, response, exerciseIdId, statusIdId, statusIdName, studentIdId, exerciseAttemptAnswerSet);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "startedAt = " + startedAt + ", " +
                "submittedAt = " + submittedAt + ", " +
                "totalScore = " + totalScore + ", " +
                "response = " + response + ", " +
                "exerciseIdId = " + exerciseIdId + ", " +
                "statusIdId = " + statusIdId + ", " +
                "statusIdName = " + statusIdName + ", " +
                "studentIdId = " + studentIdId + ", " +
                "exerciseAttemptAnswerSet = " + exerciseAttemptAnswerSet + ")";
    }

//    /**
//     * DTO for {@link com.ou.pojo.ExerciseAttemptAnswer}
//     */
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    public static class ExerciseAttemptAnswerDto implements Serializable {
//        private Integer id;
//        private String answerText;
//        private Boolean isCorrect;
//        private BigDecimal score;
//
//        public ExerciseAttemptAnswerDto() {
//        }
//
//        public ExerciseAttemptAnswerDto(Integer id, String answerText, Boolean isCorrect, BigDecimal score) {
//            this.id = id;
//            this.answerText = answerText;
//            this.isCorrect = isCorrect;
//            this.score = score;
//        }
//
//        public Integer getId() {
//            return id;
//        }
//
//        public void setId(Integer id) {
//            this.id = id;
//        }
//
//        public String getAnswerText() {
//            return answerText;
//        }
//
//        public void setAnswerText(String answerText) {
//            this.answerText = answerText;
//        }
//
//        public Boolean getIsCorrect() {
//            return isCorrect;
//        }
//
//        public void setIsCorrect(Boolean isCorrect) {
//            this.isCorrect = isCorrect;
//        }
//
//        public BigDecimal getScore() {
//            return score;
//        }
//
//        public void setScore(BigDecimal score) {
//            this.score = score;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            ExerciseAttemptAnswerDto entity = (ExerciseAttemptAnswerDto) o;
//            return Objects.equals(this.id, entity.id) &&
//                    Objects.equals(this.answerText, entity.answerText) &&
//                    Objects.equals(this.isCorrect, entity.isCorrect) &&
//                    Objects.equals(this.score, entity.score);
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(id, answerText, isCorrect, score);
//        }
//
//        @Override
//        public String toString() {
//            return getClass().getSimpleName() + "(" +
//                    "id = " + id + ", " +
//                    "answerText = " + answerText + ", " +
//                    "isCorrect = " + isCorrect + ", " +
//                    "score = " + score + ")";
//        }
//    }
}