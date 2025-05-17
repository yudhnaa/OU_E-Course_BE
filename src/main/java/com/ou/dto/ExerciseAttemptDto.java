package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.ExerciseAttempt;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

/**
 * DTO for {@link ExerciseAttempt}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExerciseAttemptDto implements Serializable {
    private Integer id;
    private Date startedAt;
    private Date submittedAt;
    private BigDecimal totalScore;
    @NotNull
    @Size(min = 1, max = 65535)
    private String response;
    private Integer exerciseIdId;
    private String exerciseIdName;
    private int exerciseIdDurationMinutes;
    private Integer statusIdId;
    private String statusIdName;
    private String statusIdDescription;
    private Integer scoreByUserIdId;
    private String scoreByUserIdLastName;
    private String scoreByUserIdFirstName;
    private String scoreByUserIdUsername;

    public ExerciseAttemptDto() {
    }

    public ExerciseAttemptDto(Integer id, Date startedAt, Date submittedAt, BigDecimal totalScore, String response, Integer exerciseIdId, String exerciseIdName, int exerciseIdDurationMinutes, Integer statusIdId, String statusIdName, String statusIdDescription, Integer scoreByUserIdId, String scoreByUserIdLastName, String scoreByUserIdFirstName, String scoreByUserIdUsername) {
        this.id = id;
        this.startedAt = startedAt;
        this.submittedAt = submittedAt;
        this.totalScore = totalScore;
        this.response = response;
        this.exerciseIdId = exerciseIdId;
        this.exerciseIdName = exerciseIdName;
        this.exerciseIdDurationMinutes = exerciseIdDurationMinutes;
        this.statusIdId = statusIdId;
        this.statusIdName = statusIdName;
        this.statusIdDescription = statusIdDescription;
        this.scoreByUserIdId = scoreByUserIdId;
        this.scoreByUserIdLastName = scoreByUserIdLastName;
        this.scoreByUserIdFirstName = scoreByUserIdFirstName;
        this.scoreByUserIdUsername = scoreByUserIdUsername;
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
                Objects.equals(this.exerciseIdName, entity.exerciseIdName) &&
                Objects.equals(this.exerciseIdDurationMinutes, entity.exerciseIdDurationMinutes) &&
                Objects.equals(this.statusIdId, entity.statusIdId) &&
                Objects.equals(this.statusIdName, entity.statusIdName) &&
                Objects.equals(this.statusIdDescription, entity.statusIdDescription) &&
                Objects.equals(this.scoreByUserIdId, entity.scoreByUserIdId) &&
                Objects.equals(this.scoreByUserIdLastName, entity.scoreByUserIdLastName) &&
                Objects.equals(this.scoreByUserIdFirstName, entity.scoreByUserIdFirstName) &&
                Objects.equals(this.scoreByUserIdUsername, entity.scoreByUserIdUsername);
    }

    public int getExerciseIdDurationMinutes() {
        return exerciseIdDurationMinutes;
    }

    public Integer getExerciseIdId() {
        return exerciseIdId;
    }

    public String getExerciseIdName() {
        return exerciseIdName;
    }

    public Integer getId() {
        return id;
    }

    public String getResponse() {
        return response;
    }

    public String getScoreByUserIdFirstName() {
        return scoreByUserIdFirstName;
    }

    public Integer getScoreByUserIdId() {
        return scoreByUserIdId;
    }

    public String getScoreByUserIdLastName() {
        return scoreByUserIdLastName;
    }

    public String getScoreByUserIdUsername() {
        return scoreByUserIdUsername;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public String getStatusIdDescription() {
        return statusIdDescription;
    }

    public Integer getStatusIdId() {
        return statusIdId;
    }

    public String getStatusIdName() {
        return statusIdName;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startedAt, submittedAt, totalScore, response, exerciseIdId, exerciseIdName, exerciseIdDurationMinutes, statusIdId, statusIdName, statusIdDescription, scoreByUserIdId, scoreByUserIdLastName, scoreByUserIdFirstName, scoreByUserIdUsername);
    }

    public void setExerciseIdDurationMinutes(int exerciseIdDurationMinutes) {
        this.exerciseIdDurationMinutes = exerciseIdDurationMinutes;
    }

    public void setExerciseIdId(Integer exerciseIdId) {
        this.exerciseIdId = exerciseIdId;
    }

    public void setExerciseIdName(String exerciseIdName) {
        this.exerciseIdName = exerciseIdName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setScoreByUserIdFirstName(String scoreByUserIdFirstName) {
        this.scoreByUserIdFirstName = scoreByUserIdFirstName;
    }

    public void setScoreByUserIdId(Integer scoreByUserIdId) {
        this.scoreByUserIdId = scoreByUserIdId;
    }

    public void setScoreByUserIdLastName(String scoreByUserIdLastName) {
        this.scoreByUserIdLastName = scoreByUserIdLastName;
    }

    public void setScoreByUserIdUsername(String scoreByUserIdUsername) {
        this.scoreByUserIdUsername = scoreByUserIdUsername;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public void setStatusIdDescription(String statusIdDescription) {
        this.statusIdDescription = statusIdDescription;
    }

    public void setStatusIdId(Integer statusIdId) {
        this.statusIdId = statusIdId;
    }

    public void setStatusIdName(String statusIdName) {
        this.statusIdName = statusIdName;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
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
                "exerciseIdName = " + exerciseIdName + ", " +
                "exerciseIdDurationMinutes = " + exerciseIdDurationMinutes + ", " +
                "statusIdId = " + statusIdId + ", " +
                "statusIdName = " + statusIdName + ", " +
                "statusIdDescription = " + statusIdDescription + ", " +
                "scoreByUserIdId = " + scoreByUserIdId + ", " +
                "scoreByUserIdLastName = " + scoreByUserIdLastName + ", " +
                "scoreByUserIdFirstName = " + scoreByUserIdFirstName + ", " +
                "scoreByUserIdUsername = " + scoreByUserIdUsername + ")";
    }
}