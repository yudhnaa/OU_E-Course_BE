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
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 *
 * @author yudhna
 */
@Entity
@Table(name = "lesson_student")
@NamedQueries({
    @NamedQuery(name = "LessonStudent.findAll", query = "SELECT l FROM LessonStudent l"),
    @NamedQuery(name = "LessonStudent.findById", query = "SELECT l FROM LessonStudent l WHERE l.id = :id"),
    @NamedQuery(name = "LessonStudent.findByIsLearn", query = "SELECT l FROM LessonStudent l WHERE l.isLearn = :isLearn"),
    @NamedQuery(name = "LessonStudent.findByLearnedAt", query = "SELECT l FROM LessonStudent l WHERE l.learnedAt = :learnedAt")})
public class LessonStudent implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "is_learn")
    private Boolean isLearn;
    @Column(name = "learned_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime learnedAt;
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lesson lessonId;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Student studentId;

    public LessonStudent() {
    }

    public LessonStudent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIsLearn() {
        return isLearn;
    }

    public void setIsLearn(Boolean isLearn) {
        this.isLearn = isLearn;
    }

    public LocalDateTime getLearnedAt() {
        return learnedAt;
    }

    public void setLearnedAt(LocalDateTime learnedAt) {
        this.learnedAt = learnedAt;
    }

    public Lesson getLessonId() {
        return lessonId;
    }

    public void setLessonId(Lesson lessonId) {
        this.lessonId = lessonId;
    }

    public Student getStudentId() {
        return studentId;
    }

    public void setStudentId(Student studentId) {
        this.studentId = studentId;
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
        if (!(object instanceof LessonStudent)) {
            return false;
        }
        LessonStudent other = (LessonStudent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.LessonStudent[ id=" + id + " ]";
    }

}
