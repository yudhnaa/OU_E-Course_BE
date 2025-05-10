package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.CourseLecturer;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link CourseLecturer}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseLecturerDto implements Serializable {
    private Integer id;
    private Integer courseIdId;
    private String courseIdName;
    private Integer lecturerIdId;
    private String lecturerIdLastName;
    private String lecturerIdFirstName;
    private String lecturerIdUsername;

    public CourseLecturerDto() {
    }

    public CourseLecturerDto(Integer id, Integer courseIdId, String courseIdName, Integer lecturerIdId, String lecturerIdLastName, String lecturerIdFirstName, String lecturerIdUsername) {
        this.id = id;
        this.courseIdId = courseIdId;
        this.courseIdName = courseIdName;
        this.lecturerIdId = lecturerIdId;
        this.lecturerIdLastName = lecturerIdLastName;
        this.lecturerIdFirstName = lecturerIdFirstName;
        this.lecturerIdUsername = lecturerIdUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseLecturerDto entity = (CourseLecturerDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.courseIdId, entity.courseIdId) &&
                Objects.equals(this.courseIdName, entity.courseIdName) &&
                Objects.equals(this.lecturerIdId, entity.lecturerIdId) &&
                Objects.equals(this.lecturerIdLastName, entity.lecturerIdLastName) &&
                Objects.equals(this.lecturerIdFirstName, entity.lecturerIdFirstName) &&
                Objects.equals(this.lecturerIdUsername, entity.lecturerIdUsername);
    }

    public Integer getCourseIdId() {
        return courseIdId;
    }

    public String getCourseIdName() {
        return courseIdName;
    }

    public Integer getId() {
        return id;
    }

    public String getLecturerIdFirstName() {
        return lecturerIdFirstName;
    }

    public Integer getLecturerIdId() {
        return lecturerIdId;
    }

    public String getLecturerIdLastName() {
        return lecturerIdLastName;
    }

    public String getLecturerIdUsername() {
        return lecturerIdUsername;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseIdId, courseIdName, lecturerIdId, lecturerIdLastName, lecturerIdFirstName, lecturerIdUsername);
    }

    public void setCourseIdId(Integer courseIdId) {
        this.courseIdId = courseIdId;
    }

    public void setCourseIdName(String courseIdName) {
        this.courseIdName = courseIdName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLecturerIdFirstName(String lecturerIdFirstName) {
        this.lecturerIdFirstName = lecturerIdFirstName;
    }

    public void setLecturerIdId(Integer lecturerIdId) {
        this.lecturerIdId = lecturerIdId;
    }

    public void setLecturerIdLastName(String lecturerIdLastName) {
        this.lecturerIdLastName = lecturerIdLastName;
    }

    public void setLecturerIdUsername(String lecturerIdUsername) {
        this.lecturerIdUsername = lecturerIdUsername;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "courseIdId = " + courseIdId + ", " +
                "courseIdName = " + courseIdName + ", " +
                "lecturerIdId = " + lecturerIdId + ", " +
                "lecturerIdLastName = " + lecturerIdLastName + ", " +
                "lecturerIdFirstName = " + lecturerIdFirstName + ", " +
                "lecturerIdUsername = " + lecturerIdUsername + ")";
    }
}