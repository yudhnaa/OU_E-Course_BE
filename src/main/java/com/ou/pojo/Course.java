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
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 *
 * @author ADMIN
 */
@Entity
@Table(name = "course")
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findById", query = "SELECT c FROM Course c WHERE c.id = :id"),
    @NamedQuery(name = "Course.findByName", query = "SELECT c FROM Course c WHERE c.name = :name"),
    @NamedQuery(name = "Course.findByImage", query = "SELECT c FROM Course c WHERE c.image = :image"),
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
    @Column(name = "date_added")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;
    @Column(name = "date_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateStart;
    @Column(name = "date_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateEnd;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Set<Exercise> exerciseSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseId")
    private Set<CourseRate> courseRateSet;
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

    public Course() {
    }

    public Course(Integer id) {
        this.id = id;
    }

    public Course(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Set<Exercise> getExerciseSet() {
        return exerciseSet;
    }

    public void setExerciseSet(Set<Exercise> exerciseSet) {
        this.exerciseSet = exerciseSet;
    }

    public Set<CourseRate> getCourseRateSet() {
        return courseRateSet;
    }

    public void setCourseRateSet(Set<CourseRate> courseRateSet) {
        this.courseRateSet = courseRateSet;
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
