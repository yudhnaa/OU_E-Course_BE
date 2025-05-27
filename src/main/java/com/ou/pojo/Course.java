/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

/**
 *
 * @author yudhna
 */
@Entity
@Table(name = "course")
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"),
    @NamedQuery(name = "Course.findByName", query = "SELECT c FROM Course c WHERE c.name = :name"),
    @NamedQuery(name = "Course.findByImage", query = "SELECT c FROM Course c WHERE c.image = :image"),
    @NamedQuery(name = "Course.findByPublicId", query = "SELECT c FROM Course c WHERE c.publicId = :publicId"),
    @NamedQuery(name = "Course.findByDateAdded", query = "SELECT c FROM Course c WHERE c.dateAdded = :dateAdded"),
    @NamedQuery(name = "Course.findByDateStart", query = "SELECT c FROM Course c WHERE c.dateStart = :dateStart"),
    @NamedQuery(name = "Course.findByDateEnd", query = "SELECT c FROM Course c WHERE c.dateEnd = :dateEnd")})
public class Course implements Serializable {

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
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "image")
    private String image;
    @Size(max = 100)
    @Column(name = "public_id")
    private String publicId;
    @Column(name = "date_added")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateAdded;
    @Column(name = "date_start")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateStart;
    @Column(name = "date_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateEnd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Set<Exercise> exerciseSet;
    @JoinColumn(name = "created_by_admin_id", referencedColumnName = "id")
    @ManyToOne
    private Admin createdByAdminId;
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @ManyToOne
    private Category categoryId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Set<CourseLecturer> courseLecturerSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Set<Test> testSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Set<Lesson> lessonSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Set<CourseStudent> courseStudentSet;

    @Transient
    private MultipartFile imageFile;


    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
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

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDateTime dateAdded) {
        this.dateAdded = dateAdded;
    }

    public LocalDateTime getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDateTime dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDateTime getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(LocalDateTime dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Set<Exercise> getExerciseSet() {
        return exerciseSet;
    }

    public void setExerciseSet(Set<Exercise> exerciseSet) {
        this.exerciseSet = exerciseSet;
    }

    public Admin getCreatedByAdminId() {
        return createdByAdminId;
    }

    public void setCreatedByAdminId(Admin createdByAdminId) {
        this.createdByAdminId = createdByAdminId;
    }

    public Category getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Category categoryId) {
        this.categoryId = categoryId;
    }

    public Set<CourseLecturer> getCourseLecturerSet() {
        return courseLecturerSet;
    }

    public void setCourseLecturerSet(Set<CourseLecturer> courseLecturerSet) {
        this.courseLecturerSet = courseLecturerSet;
    }

    public Set<Test> getTestSet() {
        return testSet;
    }

    public void setTestSet(Set<Test> testSet) {
        this.testSet = testSet;
    }

    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lessonSet) {
        this.lessonSet = lessonSet;
    }

    public Set<CourseStudent> getCourseStudentSet() {
        return courseStudentSet;
    }

    public void setCourseStudentSet(Set<CourseStudent> courseStudentSet) {
        this.courseStudentSet = courseStudentSet;
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
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.Course[ id=" + id + " ]";
    }

}