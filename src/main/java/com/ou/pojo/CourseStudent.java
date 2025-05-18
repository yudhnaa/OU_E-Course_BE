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
 * @author ADMIN
 */
@Entity
@Table(name = "course_student")
@NamedQueries({
    @NamedQuery(name = "CourseStudent.findAll", query = "SELECT c FROM CourseStudent c"),
    @NamedQuery(name = "CourseStudent.findById", query = "SELECT c FROM CourseStudent c WHERE c.id = :id"),
    @NamedQuery(name = "CourseStudent.findByName", query = "SELECT c FROM CourseStudent c WHERE c.name = :name"),
    @NamedQuery(name = "CourseStudent.findByProgress", query = "SELECT c FROM CourseStudent c WHERE c.progress = :progress")})
public class CourseStudent implements Serializable {

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
    @Column(name = "progress")
    private double progress;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseStudentId")
    private Set<CourseCertificate> courseCertificateSet;
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course courseId;
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User studentId;

    public CourseStudent() {
    }

    public CourseStudent(Integer id) {
        this.id = id;
    }

    public CourseStudent(Integer id, String name, double progress) {
        this.id = id;
        this.name = name;
        this.progress = progress;
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

    public double getProgress() {
        return progress;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public Set<CourseCertificate> getCourseCertificateSet() {
        return courseCertificateSet;
    }

    public void setCourseCertificateSet(Set<CourseCertificate> courseCertificateSet) {
        this.courseCertificateSet = courseCertificateSet;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public User getStudentId() {
        return studentId;
    }

    public void setStudentId(User studentId) {
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
        if (!(object instanceof CourseStudent)) {
            return false;
        }
        CourseStudent other = (CourseStudent) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.CourseStudent[ id=" + id + " ]";
    }
    
}
