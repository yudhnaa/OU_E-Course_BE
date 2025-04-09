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
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 *
 * @author yudhna
 */
@Entity
@Table(name = "course_certificate")
@NamedQueries({
    @NamedQuery(name = "CourseCertificate.findAll", query = "SELECT c FROM CourseCertificate c"),
    @NamedQuery(name = "CourseCertificate.findById", query = "SELECT c FROM CourseCertificate c WHERE c.id = :id"),
    @NamedQuery(name = "CourseCertificate.findByDownloadLink", query = "SELECT c FROM CourseCertificate c WHERE c.downloadLink = :downloadLink")})
public class CourseCertificate implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "download_link")
    private String downloadLink;
    @JoinColumn(name = "course_student_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private CourseStudent courseStudentId;

    public CourseCertificate() {
    }

    public CourseCertificate(Integer id) {
        this.id = id;
    }

    public CourseCertificate(Integer id, String downloadLink) {
        this.id = id;
        this.downloadLink = downloadLink;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public CourseStudent getCourseStudentId() {
        return courseStudentId;
    }

    public void setCourseStudentId(CourseStudent courseStudentId) {
        this.courseStudentId = courseStudentId;
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
        if (!(object instanceof CourseCertificate)) {
            return false;
        }
        CourseCertificate other = (CourseCertificate) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ou.pojo.CourseCertificate[ id=" + id + " ]";
    }

}
