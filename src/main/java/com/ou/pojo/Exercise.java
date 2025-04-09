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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
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
    @NamedQuery(name = "Exercise.findByDurationMinutes", query = "SELECT e FROM Exercise e WHERE e.durationMinutes = :durationMinutes")})
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciseId")
    private Set<ExerciseAttachment> exerciseAttachmentSet;
    @JoinColumn(name = "attachment_id", referencedColumnName = "id")
    @ManyToOne
    private Attachment attachmentId;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "lesson_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lesson lessonId;
    @JoinColumn(name = "created_by_user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User createdByUserId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciseId")
    private Set<Question> questionSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciseId")
    private Set<ExerciseAttempt> exerciseAttemptSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exerciseId")
    private Set<ExerciseQuestion> exerciseQuestionSet;

    public Exercise() {
    }

    public Exercise(Integer id) {
        this.id = id;
    }

    public Exercise(Integer id, String name, int durationMinutes) {
        this.id = id;
        this.name = name;
        this.durationMinutes = durationMinutes;
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

    public Set<ExerciseAttachment> getExerciseAttachmentSet() {
        return exerciseAttachmentSet;
    }

    public void setExerciseAttachmentSet(Set<ExerciseAttachment> exerciseAttachmentSet) {
        this.exerciseAttachmentSet = exerciseAttachmentSet;
    }

    public Attachment getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(Attachment attachmentId) {
        this.attachmentId = attachmentId;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Lesson getLessonId() {
        return lessonId;
    }

    public void setLessonId(Lesson lessonId) {
        this.lessonId = lessonId;
    }

    public User getCreatedByUserId() {
        return createdByUserId;
    }

    public void setCreatedByUserId(User createdByUserId) {
        this.createdByUserId = createdByUserId;
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

    public Set<ExerciseQuestion> getExerciseQuestionSet() {
        return exerciseQuestionSet;
    }

    public void setExerciseQuestionSet(Set<ExerciseQuestion> exerciseQuestionSet) {
        this.exerciseQuestionSet = exerciseQuestionSet;
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
