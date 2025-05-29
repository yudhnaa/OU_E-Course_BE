/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

/**
 *
 * @author yudhna
 */
@Entity
@Table(name = "exercise")
@NamedQueries({
    @NamedQuery(name = "Exercise.findAll", query = "SELECT e FROM Exercise e"),
    @NamedQuery(name = "Exercise.findById", query = "SELECT e FROM Exercise e WHERE e.id = :id"),
    @NamedQuery(name = "Exercise.findByName", query = "SELECT e FROM Exercise e WHERE e.name = :name"),
    @NamedQuery(name = "Exercise.findByDurationMinutes", query = "SELECT e FROM Exercise e WHERE e.durationMinutes = :durationMinutes"),
    @NamedQuery(name = "Exercise.findByMaxScore", query = "SELECT e FROM Exercise e WHERE e.maxScore = :maxScore")})
public class Exercise implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "duration_minutes")
    private int durationMinutes;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "max_score")
    private BigDecimal maxScore;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciseId")
    private Set<ExerciseAttachment> exerciseAttachmentSet;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lecturer createdByUserId;
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lesson lessonId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciseId")
    private Set<Question> questionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciseId")
    private Set<ExerciseAttempt> exerciseAttemptSet;

    public Exercise() {
    }

    public Exercise(Integer id) {
        this.id = id;
    }

    public Exercise(Integer id, String name, int durationMinutes, BigDecimal maxScore) {
        this.id = id;
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.maxScore = maxScore;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public BigDecimal getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(BigDecimal maxScore) {
        this.maxScore = maxScore;
    }

    public Set<ExerciseAttachment> getExerciseAttachmentSet() {
        return exerciseAttachmentSet;
    }

    public void setExerciseAttachmentSet(Set<ExerciseAttachment> exerciseAttachmentSet) {
        this.exerciseAttachmentSet = exerciseAttachmentSet;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Lecturer getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(Lecturer createdByUserId) {
        this.createdByUserId = createdByUserId;
    }

    public Lesson getLessonId() {
        return lessonId;
    }

    public void setLessonId(Lesson lessonId) {
        this.lessonId = lessonId;
    }

    public Set<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(Set<Question> questionSet) {
        this.questionSet = questionSet;
    }

    public Set<ExerciseAttempt> getExerciseAttemptSet() {
        return exerciseAttemptSet;
    }

    public void setExerciseAttemptSet(Set<ExerciseAttempt> exerciseAttemptSet) {
        this.exerciseAttemptSet = exerciseAttemptSet;
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
        if (!(object instanceof Exercise)) {
            return false;
        }
        Exercise other = (Exercise) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.Exercise[ id=" + id + " ]";
    }

}
