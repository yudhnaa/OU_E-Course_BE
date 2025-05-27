/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.pojo;

import jakarta.persistence.Basic;
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
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "exercise_attempt_answer")
@NamedQueries({
    @NamedQuery(name = "ExerciseAttemptAnswer.findAll", query = "SELECT e FROM ExerciseAttemptAnswer e"),
    @NamedQuery(name = "ExerciseAttemptAnswer.findById", query = "SELECT e FROM ExerciseAttemptAnswer e WHERE e.id = :id"),
    @NamedQuery(name = "ExerciseAttemptAnswer.findByIsCorrect", query = "SELECT e FROM ExerciseAttemptAnswer e WHERE e.isCorrect = :isCorrect"),
    @NamedQuery(name = "ExerciseAttemptAnswer.findByScore", query = "SELECT e FROM ExerciseAttemptAnswer e WHERE e.score = :score")})
public class ExerciseAttemptAnswer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Lob
    @Size(max = 65535)
    @Column(name = "answer_text")
    private String answerText;
    @Column(name = "is_correct")
    private Boolean isCorrect;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "score")
    private BigDecimal score;
    @JoinColumn(name = "attempt_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ExerciseAttempt attemptId;
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Question questionId;

    public ExerciseAttemptAnswer() {
    }

    public ExerciseAttemptAnswer(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public ExerciseAttempt getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(ExerciseAttempt attemptId) {
        this.attemptId = attemptId;
    }

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
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
        if (!(object instanceof ExerciseAttemptAnswer)) {
            return false;
        }
        ExerciseAttemptAnswer other = (ExerciseAttemptAnswer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.ExerciseAttemptAnswer[ id=" + id + " ]";
    }
    
}
