package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link com.ou.pojo.CourseCertificate}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseCertificateDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 255)
    private String downloadLink;
    private Integer courseStudentIdId;
    private double courseStudentIdProgress;
    private Integer courseStudentIdCourseIdId;
    private String courseStudentIdCourseIdName;
    private String courseStudentIdCourseIdImage;
    private Integer courseStudentIdStudentIdId;
    private Integer courseStudentIdStudentIdUserIdId;
    private String courseStudentIdStudentIdUserIdLastName;
    private String courseStudentIdStudentIdUserIdFirstName;
    private String courseStudentIdStudentIdUserIdUsername;
    private String courseStudentIdStudentIdUserIdAvatar;
    private String courseStudentIdStudentIdUserIdEmail;
    private Integer courseStudentIdStudentIdUserIdUserRoleIdId;
    private String courseStudentIdStudentIdUserIdUserRoleIdName;

    public CourseCertificateDto() {
    }

    public CourseCertificateDto(Integer id, String downloadLink, Integer courseStudentIdId, double courseStudentIdProgress, Integer courseStudentIdCourseIdId, String courseStudentIdCourseIdName, String courseStudentIdCourseIdImage, Integer courseStudentIdStudentIdId, Integer courseStudentIdStudentIdUserIdId, String courseStudentIdStudentIdUserIdLastName, String courseStudentIdStudentIdUserIdFirstName, String courseStudentIdStudentIdUserIdUsername, String courseStudentIdStudentIdUserIdAvatar, String courseStudentIdStudentIdUserIdEmail, Integer courseStudentIdStudentIdUserIdUserRoleIdId, String courseStudentIdStudentIdUserIdUserRoleIdName) {
        this.id = id;
        this.downloadLink = downloadLink;
        this.courseStudentIdId = courseStudentIdId;
        this.courseStudentIdProgress = courseStudentIdProgress;
        this.courseStudentIdCourseIdId = courseStudentIdCourseIdId;
        this.courseStudentIdCourseIdName = courseStudentIdCourseIdName;
        this.courseStudentIdCourseIdImage = courseStudentIdCourseIdImage;
        this.courseStudentIdStudentIdId = courseStudentIdStudentIdId;
        this.courseStudentIdStudentIdUserIdId = courseStudentIdStudentIdUserIdId;
        this.courseStudentIdStudentIdUserIdLastName = courseStudentIdStudentIdUserIdLastName;
        this.courseStudentIdStudentIdUserIdFirstName = courseStudentIdStudentIdUserIdFirstName;
        this.courseStudentIdStudentIdUserIdUsername = courseStudentIdStudentIdUserIdUsername;
        this.courseStudentIdStudentIdUserIdAvatar = courseStudentIdStudentIdUserIdAvatar;
        this.courseStudentIdStudentIdUserIdEmail = courseStudentIdStudentIdUserIdEmail;
        this.courseStudentIdStudentIdUserIdUserRoleIdId = courseStudentIdStudentIdUserIdUserRoleIdId;
        this.courseStudentIdStudentIdUserIdUserRoleIdName = courseStudentIdStudentIdUserIdUserRoleIdName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseCertificateDto entity = (CourseCertificateDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.downloadLink, entity.downloadLink) &&
                Objects.equals(this.courseStudentIdId, entity.courseStudentIdId) &&
                Objects.equals(this.courseStudentIdProgress, entity.courseStudentIdProgress) &&
                Objects.equals(this.courseStudentIdCourseIdId, entity.courseStudentIdCourseIdId) &&
                Objects.equals(this.courseStudentIdCourseIdName, entity.courseStudentIdCourseIdName) &&
                Objects.equals(this.courseStudentIdCourseIdImage, entity.courseStudentIdCourseIdImage) &&
                Objects.equals(this.courseStudentIdStudentIdId, entity.courseStudentIdStudentIdId) &&
                Objects.equals(this.courseStudentIdStudentIdUserIdId, entity.courseStudentIdStudentIdUserIdId) &&
                Objects.equals(this.courseStudentIdStudentIdUserIdLastName, entity.courseStudentIdStudentIdUserIdLastName) &&
                Objects.equals(this.courseStudentIdStudentIdUserIdFirstName, entity.courseStudentIdStudentIdUserIdFirstName) &&
                Objects.equals(this.courseStudentIdStudentIdUserIdUsername, entity.courseStudentIdStudentIdUserIdUsername) &&
                Objects.equals(this.courseStudentIdStudentIdUserIdAvatar, entity.courseStudentIdStudentIdUserIdAvatar) &&
                Objects.equals(this.courseStudentIdStudentIdUserIdEmail, entity.courseStudentIdStudentIdUserIdEmail) &&
                Objects.equals(this.courseStudentIdStudentIdUserIdUserRoleIdId, entity.courseStudentIdStudentIdUserIdUserRoleIdId) &&
                Objects.equals(this.courseStudentIdStudentIdUserIdUserRoleIdName, entity.courseStudentIdStudentIdUserIdUserRoleIdName);
    }

    public Integer getCourseStudentIdCourseIdId() {
        return courseStudentIdCourseIdId;
    }

    public String getCourseStudentIdCourseIdImage() {
        return courseStudentIdCourseIdImage;
    }

    public String getCourseStudentIdCourseIdName() {
        return courseStudentIdCourseIdName;
    }

    public Integer getCourseStudentIdId() {
        return courseStudentIdId;
    }

    public double getCourseStudentIdProgress() {
        return courseStudentIdProgress;
    }

    public Integer getCourseStudentIdStudentIdId() {
        return courseStudentIdStudentIdId;
    }

    public String getCourseStudentIdStudentIdUserIdAvatar() {
        return courseStudentIdStudentIdUserIdAvatar;
    }

    public String getCourseStudentIdStudentIdUserIdEmail() {
        return courseStudentIdStudentIdUserIdEmail;
    }

    public String getCourseStudentIdStudentIdUserIdFirstName() {
        return courseStudentIdStudentIdUserIdFirstName;
    }

    public Integer getCourseStudentIdStudentIdUserIdId() {
        return courseStudentIdStudentIdUserIdId;
    }

    public String getCourseStudentIdStudentIdUserIdLastName() {
        return courseStudentIdStudentIdUserIdLastName;
    }

    public Integer getCourseStudentIdStudentIdUserIdUserRoleIdId() {
        return courseStudentIdStudentIdUserIdUserRoleIdId;
    }

    public String getCourseStudentIdStudentIdUserIdUserRoleIdName() {
        return courseStudentIdStudentIdUserIdUserRoleIdName;
    }

    public String getCourseStudentIdStudentIdUserIdUsername() {
        return courseStudentIdStudentIdUserIdUsername;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, downloadLink, courseStudentIdId, courseStudentIdProgress, courseStudentIdCourseIdId, courseStudentIdCourseIdName, courseStudentIdCourseIdImage, courseStudentIdStudentIdId, courseStudentIdStudentIdUserIdId, courseStudentIdStudentIdUserIdLastName, courseStudentIdStudentIdUserIdFirstName, courseStudentIdStudentIdUserIdUsername, courseStudentIdStudentIdUserIdAvatar, courseStudentIdStudentIdUserIdEmail, courseStudentIdStudentIdUserIdUserRoleIdId, courseStudentIdStudentIdUserIdUserRoleIdName);
    }

    public void setCourseStudentIdCourseIdId(Integer courseStudentIdCourseIdId) {
        this.courseStudentIdCourseIdId = courseStudentIdCourseIdId;
    }

    public void setCourseStudentIdCourseIdImage(String courseStudentIdCourseIdImage) {
        this.courseStudentIdCourseIdImage = courseStudentIdCourseIdImage;
    }

    public void setCourseStudentIdCourseIdName(String courseStudentIdCourseIdName) {
        this.courseStudentIdCourseIdName = courseStudentIdCourseIdName;
    }

    public void setCourseStudentIdId(Integer courseStudentIdId) {
        this.courseStudentIdId = courseStudentIdId;
    }

    public void setCourseStudentIdProgress(double courseStudentIdProgress) {
        this.courseStudentIdProgress = courseStudentIdProgress;
    }

    public void setCourseStudentIdStudentIdId(Integer courseStudentIdStudentIdId) {
        this.courseStudentIdStudentIdId = courseStudentIdStudentIdId;
    }

    public void setCourseStudentIdStudentIdUserIdAvatar(String courseStudentIdStudentIdUserIdAvatar) {
        this.courseStudentIdStudentIdUserIdAvatar = courseStudentIdStudentIdUserIdAvatar;
    }

    public void setCourseStudentIdStudentIdUserIdEmail(String courseStudentIdStudentIdUserIdEmail) {
        this.courseStudentIdStudentIdUserIdEmail = courseStudentIdStudentIdUserIdEmail;
    }

    public void setCourseStudentIdStudentIdUserIdFirstName(String courseStudentIdStudentIdUserIdFirstName) {
        this.courseStudentIdStudentIdUserIdFirstName = courseStudentIdStudentIdUserIdFirstName;
    }

    public void setCourseStudentIdStudentIdUserIdId(Integer courseStudentIdStudentIdUserIdId) {
        this.courseStudentIdStudentIdUserIdId = courseStudentIdStudentIdUserIdId;
    }

    public void setCourseStudentIdStudentIdUserIdLastName(String courseStudentIdStudentIdUserIdLastName) {
        this.courseStudentIdStudentIdUserIdLastName = courseStudentIdStudentIdUserIdLastName;
    }

    public void setCourseStudentIdStudentIdUserIdUserRoleIdId(Integer courseStudentIdStudentIdUserIdUserRoleIdId) {
        this.courseStudentIdStudentIdUserIdUserRoleIdId = courseStudentIdStudentIdUserIdUserRoleIdId;
    }

    public void setCourseStudentIdStudentIdUserIdUserRoleIdName(String courseStudentIdStudentIdUserIdUserRoleIdName) {
        this.courseStudentIdStudentIdUserIdUserRoleIdName = courseStudentIdStudentIdUserIdUserRoleIdName;
    }

    public void setCourseStudentIdStudentIdUserIdUsername(String courseStudentIdStudentIdUserIdUsername) {
        this.courseStudentIdStudentIdUserIdUsername = courseStudentIdStudentIdUserIdUsername;
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
                "courseStudentIdId = " + courseStudentIdId + ", " +
                "courseStudentIdProgress = " + courseStudentIdProgress + ", " +
                "courseStudentIdCourseIdId = " + courseStudentIdCourseIdId + ", " +
                "courseStudentIdCourseIdName = " + courseStudentIdCourseIdName + ", " +
                "courseStudentIdCourseIdImage = " + courseStudentIdCourseIdImage + ", " +
                "courseStudentIdStudentIdId = " + courseStudentIdStudentIdId + ", " +
                "courseStudentIdStudentIdUserIdId = " + courseStudentIdStudentIdUserIdId + ", " +
                "courseStudentIdStudentIdUserIdLastName = " + courseStudentIdStudentIdUserIdLastName + ", " +
                "courseStudentIdStudentIdUserIdFirstName = " + courseStudentIdStudentIdUserIdFirstName + ", " +
                "courseStudentIdStudentIdUserIdUsername = " + courseStudentIdStudentIdUserIdUsername + ", " +
                "courseStudentIdStudentIdUserIdAvatar = " + courseStudentIdStudentIdUserIdAvatar + ", " +
                "courseStudentIdStudentIdUserIdEmail = " + courseStudentIdStudentIdUserIdEmail + ", " +
                "courseStudentIdStudentIdUserIdUserRoleIdId = " + courseStudentIdStudentIdUserIdUserRoleIdId + ", " +
                "courseStudentIdStudentIdUserIdUserRoleIdName = " + courseStudentIdStudentIdUserIdUserRoleIdName + ")";
    }
}