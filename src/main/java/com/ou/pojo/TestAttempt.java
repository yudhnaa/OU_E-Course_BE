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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author yudhna
 */
@Entity
@Table(name = "test_attempt")
@NamedQueries({
    @NamedQuery(name = "TestAttempt.findAll", query = "SELECT t FROM TestAttempt t"),
    @NamedQuery(name = "TestAttempt.findById", query = "SELECT t FROM TestAttempt t WHERE t.id = :id"),
    @NamedQuery(name = "TestAttempt.findByStartedAt", query = "SELECT t FROM TestAttempt t WHERE t.startedAt = :startedAt"),
    @NamedQuery(name = "TestAttempt.findBySubmittedAt", query = "SELECT t FROM TestAttempt t WHERE t.submittedAt = :submittedAt"),
    @NamedQuery(name = "TestAttempt.findByTotalScore", query = "SELECT t FROM TestAttempt t WHERE t.totalScore = :totalScore")})
public class TestAttempt implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "started_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startedAt;
    @Column(name = "submitted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedAt;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_score")
    private BigDecimal totalScore;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student userId;
    @JoinColumn(name = "test_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Test testId;

    public TestAttempt() {
    }

    public TestAttempt(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Date submittedAt) {
        this.submittedAt = submittedAt;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public Student getUserId() {
        return userId;
    }

    public void setUserId(Student userId) {
        this.userId = userId;
    }

    public Test getTestId() {
        return testId;
    }

    public void setTestId(Test testId) {
        this.testId = testId;
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
        if (!(object instanceof TestAttempt)) {
            return false;
        }
        TestAttempt other = (TestAttempt) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.TestAttempt[ id=" + id + " ]";
    }

}
