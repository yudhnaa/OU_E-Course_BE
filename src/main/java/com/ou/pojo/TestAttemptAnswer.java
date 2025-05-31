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
import java.util.Objects;

/**
 *
 * @author yudhna
 */
@Entity
@Table(name = "test_attempt_answer")
@NamedQueries({
    @NamedQuery(name = "TestAttemptAnswer.findAll", query = "SELECT t FROM TestAttemptAnswer t"),
    @NamedQuery(name = "TestAttemptAnswer.findById", query = "SELECT t FROM TestAttemptAnswer t WHERE t.id = :id"),
    @NamedQuery(name = "TestAttemptAnswer.findByIsCorrect", query = "SELECT t FROM TestAttemptAnswer t WHERE t.isCorrect = :isCorrect"),
    @NamedQuery(name = "TestAttemptAnswer.findByScore", query = "SELECT t FROM TestAttemptAnswer t WHERE t.score = :score")})
public class TestAttemptAnswer implements Serializable {

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
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Question questionId;
    @JoinColumn(name = "attempt_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TestAttempt attemptId;

    public TestAttemptAnswer() {
    }

    public TestAttemptAnswer(Integer id) {
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

    public Question getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Question questionId) {
        this.questionId = questionId;
    }

    public TestAttempt getAttemptId() {
        return attemptId;
    }

    public void setAttemptId(TestAttempt attemptId) {
        this.attemptId = attemptId;
    }

    @Override
    public int hashCode() {
//        int hash = 0;
//        hash += (id != null ? id.hashCode() : 0);
//        return hash;
        return Objects.hash(id, questionId, answerText);
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TestAttemptAnswer)) {
            return false;
        }
        TestAttemptAnswer other = (TestAttemptAnswer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.TestAttemptAnswer[ id=" + id + " ]";
    }

}
