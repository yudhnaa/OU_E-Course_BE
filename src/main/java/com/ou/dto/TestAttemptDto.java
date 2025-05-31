package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

/**
 * DTO for {@link com.ou.pojo.TestAttempt}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestAttemptDto implements Serializable {
    private Integer id;
    private LocalDateTime startedAt;
    private LocalDateTime submittedAt;
    private BigDecimal totalScore;
    private Set<TestAttemptAnswerDto> testAttemptAnswerSet;
    private Integer userIdId;
    private Integer testIdId;

    public TestAttemptDto() {
    }

    public TestAttemptDto(Integer id, LocalDateTime startedAt, LocalDateTime submittedAt, BigDecimal totalScore, Set<TestAttemptAnswerDto> testAttemptAnswerSet, Integer userIdId, Integer testIdId) {
        this.id = id;
        this.startedAt = startedAt;
        this.submittedAt = submittedAt;
        this.totalScore = totalScore;
        this.testAttemptAnswerSet = testAttemptAnswerSet;
        this.userIdId = userIdId;
        this.testIdId = testIdId;
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

    public Set<TestAttemptAnswerDto> getTestAttemptAnswerSet() {
        return testAttemptAnswerSet;
    }

    public void setTestAttemptAnswerSet(Set<TestAttemptAnswerDto> testAttemptAnswerSet) {
        this.testAttemptAnswerSet = testAttemptAnswerSet;
    }

    public Integer getUserIdId() {
        return userIdId;
    }

    public void setUserIdId(Integer userIdId) {
        this.userIdId = userIdId;
    }

    public Integer getTestIdId() {
        return testIdId;
    }

    public void setTestIdId(Integer testIdId) {
        this.testIdId = testIdId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestAttemptDto entity = (TestAttemptDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.startedAt, entity.startedAt) &&
                Objects.equals(this.submittedAt, entity.submittedAt) &&
                Objects.equals(this.totalScore, entity.totalScore) &&
                Objects.equals(this.testAttemptAnswerSet, entity.testAttemptAnswerSet) &&
                Objects.equals(this.userIdId, entity.userIdId) &&
                Objects.equals(this.testIdId, entity.testIdId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startedAt, submittedAt, totalScore, testAttemptAnswerSet, userIdId, testIdId);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "startedAt = " + startedAt + ", " +
                "submittedAt = " + submittedAt + ", " +
                "totalScore = " + totalScore + ", " +
                "testAttemptAnswerSet = " + testAttemptAnswerSet + ", " +
                "userIdId = " + userIdId + ", " +
                "testIdId = " + testIdId + ")";
    }

    /**
     * DTO for {@link com.ou.pojo.TestAttemptAnswer}
     */
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    public static class TestAttemptAnswerDto implements Serializable {
//        private Integer id;
//        private String answerText;
//        private Boolean isCorrect;
//        private BigDecimal score;
//
//        public TestAttemptAnswerDto() {
//        }
//
//        public TestAttemptAnswerDto(Integer id, String answerText, Boolean isCorrect, BigDecimal score) {
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
//            TestAttemptAnswerDto entity = (TestAttemptAnswerDto) o;
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