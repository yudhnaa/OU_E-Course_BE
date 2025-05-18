/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.List;
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
    @NamedQuery(name = "Lesson.findByEmbedLink", query = "SELECT l FROM Lesson l WHERE l.embedLink = :embedLink"),
    @NamedQuery(name = "Lesson.findByImage", query = "SELECT l FROM Lesson l WHERE l.image = :image")})
public class Lesson implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
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
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lessonId")
    private Set<Exercise> exerciseSet;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "lesson_type_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LessonType lessonTypeId;
    @JoinColumn(name = "user_upload_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userUploadId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lessonId")
    private Set<LessonAttachment> lessonAttachmentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lessonId")
    private Set<LessonStudent> lessonStudentSet;


    @Transient
    private MultipartFile thumbnailImage;
    @Transient
    private List<MultipartFile> lessonAttachments;


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

    public MultipartFile getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(MultipartFile thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
    }

    public List<MultipartFile> getLessonAttachments() {
        return lessonAttachments;
    }

    public void setLessonAttachments(List<MultipartFile> lessonAttachments) {
        this.lessonAttachments = lessonAttachments;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Set<Exercise> getExerciseSet() {
        return exerciseSet;
    }

    public void setExerciseSet(Set<Exercise> exerciseSet) {
        this.exerciseSet = exerciseSet;
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

    public User getUserUploadId() {
        return userUploadId;
    }

    public void setUserUploadId(User userUploadId) {
        this.userUploadId = userUploadId;
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
