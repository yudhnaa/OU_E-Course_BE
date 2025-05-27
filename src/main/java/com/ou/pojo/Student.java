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
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author yudhna
 */
@Entity
@Table(name = "student")
@NamedQueries({
    @NamedQuery(name = "Student.findAll", query = "SELECT s FROM Student s"),
    @NamedQuery(name = "Student.findById", query = "SELECT s FROM Student s WHERE s.id = :id")})
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private Set<TestAttempt> testAttemptSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Set<LessonStudent> lessonStudentSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentId")
    private Set<CourseStudent> courseStudentSet;

    public Student() {
    }

    public Student(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Set<TestAttempt> getTestAttemptSet() {
        return testAttemptSet;
    }

    public void setTestAttemptSet(Set<TestAttempt> testAttemptSet) {
        this.testAttemptSet = testAttemptSet;
    }

    public Set<LessonStudent> getLessonStudentSet() {
        return lessonStudentSet;
    }

    public void setLessonStudentSet(Set<LessonStudent> lessonStudentSet) {
        this.lessonStudentSet = lessonStudentSet;
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
        if (!(object instanceof Student)) {
            return false;
        }
        Student other = (Student) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.Student[ id=" + id + " ]";
    }

}
