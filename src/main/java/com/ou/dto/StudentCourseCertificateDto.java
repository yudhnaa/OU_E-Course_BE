package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.CourseCertificate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link CourseCertificate}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentCourseCertificateDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 255)
    private String downloadLink;
    private Integer courseStudentIdCourseIdId;
    private String courseStudentIdCourseIdName;
    private Integer courseStudentIdStudentIdId;
    private String courseStudentIdStudentIdLastName;
    private String courseStudentIdStudentIdFirstName;
    private String courseStudentIdStudentIdUsername;

    public StudentCourseCertificateDto() {
    }

    public StudentCourseCertificateDto(Integer id, String downloadLink, Integer courseStudentIdCourseIdId, String courseStudentIdCourseIdName, Integer courseStudentIdStudentIdId, String courseStudentIdStudentIdLastName, String courseStudentIdStudentIdFirstName, String courseStudentIdStudentIdUsername) {
        this.id = id;
        this.downloadLink = downloadLink;
        this.courseStudentIdCourseIdId = courseStudentIdCourseIdId;
        this.courseStudentIdCourseIdName = courseStudentIdCourseIdName;
        this.courseStudentIdStudentIdId = courseStudentIdStudentIdId;
        this.courseStudentIdStudentIdLastName = courseStudentIdStudentIdLastName;
        this.courseStudentIdStudentIdFirstName = courseStudentIdStudentIdFirstName;
        this.courseStudentIdStudentIdUsername = courseStudentIdStudentIdUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCourseCertificateDto entity = (StudentCourseCertificateDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.downloadLink, entity.downloadLink) &&
                Objects.equals(this.courseStudentIdCourseIdId, entity.courseStudentIdCourseIdId) &&
                Objects.equals(this.courseStudentIdCourseIdName, entity.courseStudentIdCourseIdName) &&
                Objects.equals(this.courseStudentIdStudentIdId, entity.courseStudentIdStudentIdId) &&
                Objects.equals(this.courseStudentIdStudentIdLastName, entity.courseStudentIdStudentIdLastName) &&
                Objects.equals(this.courseStudentIdStudentIdFirstName, entity.courseStudentIdStudentIdFirstName) &&
                Objects.equals(this.courseStudentIdStudentIdUsername, entity.courseStudentIdStudentIdUsername);
    }

    public Integer getCourseStudentIdCourseIdId() {
        return courseStudentIdCourseIdId;
    }

    public String getCourseStudentIdCourseIdName() {
        return courseStudentIdCourseIdName;
    }

    public String getCourseStudentIdStudentIdFirstName() {
        return courseStudentIdStudentIdFirstName;
    }

    public Integer getCourseStudentIdStudentIdId() {
        return courseStudentIdStudentIdId;
    }

    public String getCourseStudentIdStudentIdLastName() {
        return courseStudentIdStudentIdLastName;
    }

    public String getCourseStudentIdStudentIdUsername() {
        return courseStudentIdStudentIdUsername;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, downloadLink, courseStudentIdCourseIdId, courseStudentIdCourseIdName, courseStudentIdStudentIdId, courseStudentIdStudentIdLastName, courseStudentIdStudentIdFirstName, courseStudentIdStudentIdUsername);
    }

    public void setCourseStudentIdCourseIdId(Integer courseStudentIdCourseIdId) {
        this.courseStudentIdCourseIdId = courseStudentIdCourseIdId;
    }

    public void setCourseStudentIdCourseIdName(String courseStudentIdCourseIdName) {
        this.courseStudentIdCourseIdName = courseStudentIdCourseIdName;
    }

    public void setCourseStudentIdStudentIdFirstName(String courseStudentIdStudentIdFirstName) {
        this.courseStudentIdStudentIdFirstName = courseStudentIdStudentIdFirstName;
    }

    public void setCourseStudentIdStudentIdId(Integer courseStudentIdStudentIdId) {
        this.courseStudentIdStudentIdId = courseStudentIdStudentIdId;
    }

    public void setCourseStudentIdStudentIdLastName(String courseStudentIdStudentIdLastName) {
        this.courseStudentIdStudentIdLastName = courseStudentIdStudentIdLastName;
    }

    public void setCourseStudentIdStudentIdUsername(String courseStudentIdStudentIdUsername) {
        this.courseStudentIdStudentIdUsername = courseStudentIdStudentIdUsername;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "downloadLink = " + downloadLink + ", " +
                "courseStudentIdCourseIdId = " + courseStudentIdCourseIdId + ", " +
                "courseStudentIdCourseIdName = " + courseStudentIdCourseIdName + ", " +
                "courseStudentIdStudentIdId = " + courseStudentIdStudentIdId + ", " +
                "courseStudentIdStudentIdLastName = " + courseStudentIdStudentIdLastName + ", " +
                "courseStudentIdStudentIdFirstName = " + courseStudentIdStudentIdFirstName + ", " +
                "courseStudentIdStudentIdUsername = " + courseStudentIdStudentIdUsername + ")";
    }
}