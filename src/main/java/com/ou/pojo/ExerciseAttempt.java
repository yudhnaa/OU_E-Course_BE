/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.pojo;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "exercise_attempt")
@NamedQueries({
    @NamedQuery(name = "ExerciseAttempt.findAll", query = "SELECT e FROM ExerciseAttempt e"),
    @NamedQuery(name = "ExerciseAttempt.findById", query = "SELECT e FROM ExerciseAttempt e WHERE e.id = :id"),
    @NamedQuery(name = "ExerciseAttempt.findByStartedAt", query = "SELECT e FROM ExerciseAttempt e WHERE e.startedAt = :startedAt"),
    @NamedQuery(name = "ExerciseAttempt.findBySubmittedAt", query = "SELECT e FROM ExerciseAttempt e WHERE e.submittedAt = :submittedAt"),
    @NamedQuery(name = "ExerciseAttempt.findByTotalScore", query = "SELECT e FROM ExerciseAttempt e WHERE e.totalScore = :totalScore")})
public class ExerciseAttempt implements Serializable {
    @Lob
    @Size(max = 65535)
    @Column(name = "response")
    private String response;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student studentId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "attemptId")
    private Set<ExerciseAttemptAnswer> exerciseAttemptAnswerSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "started_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime startedAt;
    @Column(name = "submitted_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime submittedAt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_score")
    private BigDecimal totalScore;
    @JoinColumn(name = "exercise_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Exercise exerciseId;
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ExerciseScoreStatus statusId;
    @JoinColumn(name = "score_by_user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lecturer scoreByUserId;

    public ExerciseAttempt() {
    }

    public ExerciseAttempt(Integer id) {
        this.id = id;
    }

    public ExerciseAttempt(Integer id, String response) {
        this.id = id;
        this.response = response;
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


    public Exercise getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Exercise exerciseId) {
        this.exerciseId = exerciseId;
    }

    public ExerciseScoreStatus getStatusId() {
        return statusId;
    }

    public void setStatusId(ExerciseScoreStatus statusId) {
        this.statusId = statusId;
    }

    public Lecturer getScoreByUserId() {
        return scoreByUserId;
    }

    public void setScoreByUserId(Lecturer scoreByUserId) {
        this.scoreByUserId = scoreByUserId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ExerciseAttempt)) {
            return false;
        }
        ExerciseAttempt other = (ExerciseAttempt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.ExerciseAttempt[ id=" + id + " ]";
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    public Set<ExerciseAttemptAnswer> getExerciseAttemptAnswerSet() {
        return exerciseAttemptAnswerSet;
    }

    public void setExerciseAttemptAnswerSet(Set<ExerciseAttemptAnswer> exerciseAttemptAnswerSet) {
        this.exerciseAttemptAnswerSet = exerciseAttemptAnswerSet;
    }
}
