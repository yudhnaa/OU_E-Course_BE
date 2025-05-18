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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "lesson")
@NamedQueries({
    @NamedQuery(name = "Lesson.findAll", query = "SELECT l FROM Lesson l"),
    @NamedQuery(name = "Lesson.findById", query = "SELECT l FROM Lesson l WHERE l.id = :id"),
    @NamedQuery(name = "Lesson.findByName", query = "SELECT l FROM Lesson l WHERE l.name = :name"),
    @NamedQuery(name = "Lesson.findByEmbedLink", query = "SELECT l FROM Lesson l WHERE l.embedLink = :embedLink")})
public class Lesson implements Serializable {

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
    @Size(min = 1, max = 255)
    @Column(name = "embed_link")
    private String embedLink;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lessonId")
    private Set<Exercise> exerciseSet;
    @JoinColumn(name = "user_upload_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Admin userUploadId;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "lesson_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LessonType lessonTypeId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lessonId")
    private Set<LessonAttachment> lessonAttachmentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lessonId")
    private Set<LessonStudent> lessonStudentSet;

    public Lesson() {
    }

    public Lesson(Integer id) {
        this.id = id;
    }

    public Lesson(Integer id, String name, String embedLink) {
        this.id = id;
        this.name = name;
        this.embedLink = embedLink;
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

    public String getEmbedLink() {
        return embedLink;
    }

    public void setEmbedLink(String embedLink) {
        this.embedLink = embedLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Exercise> getExerciseSet() {
        return exerciseSet;
    }

    public void setExerciseSet(Set<Exercise> exerciseSet) {
        this.exerciseSet = exerciseSet;
    }

    public Admin getUserUploadId() {
        return userUploadId;
    }

    public void setUserUploadId(Admin userUploadId) {
        this.userUploadId = userUploadId;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public LessonType getLessonTypeId() {
        return lessonTypeId;
    }

    public void setLessonTypeId(LessonType lessonTypeId) {
        this.lessonTypeId = lessonTypeId;
    }

    public Set<LessonAttachment> getLessonAttachmentSet() {
        return lessonAttachmentSet;
    }

    public void setLessonAttachmentSet(Set<LessonAttachment> lessonAttachmentSet) {
        this.lessonAttachmentSet = lessonAttachmentSet;
    }

    public Set<LessonStudent> getLessonStudentSet() {
        return lessonStudentSet;
    }

    public void setLessonStudentSet(Set<LessonStudent> lessonStudentSet) {
        this.lessonStudentSet = lessonStudentSet;
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
        if (!(object instanceof Lesson)) {
            return false;
        }
        Lesson other = (Lesson) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.Lesson[ id=" + id + " ]";
    }
    
}
