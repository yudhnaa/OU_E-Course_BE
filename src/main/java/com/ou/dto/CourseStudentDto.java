package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.CourseStudent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link CourseStudent}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseStudentDto implements Serializable {
    private Integer id;
    @NotNull
    @Size(min = 1, max = 50)
    private String name;
    private double progress;
    private Integer courseIdId;
    private String courseIdName;
    private Integer studentIdId;
    private String studentIdUsername;

    public CourseStudentDto() {
    }

    public CourseStudentDto(Integer id, String name, double progress, Integer courseIdId, String courseIdName, Integer studentIdId, String studentIdUsername) {
        this.id = id;
        this.name = name;
        this.progress = progress;
        this.courseIdId = courseIdId;
        this.courseIdName = courseIdName;
        this.studentIdId = studentIdId;
        this.studentIdUsername = studentIdUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseStudentDto entity = (CourseStudentDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.name, entity.name) &&
                Objects.equals(this.progress, entity.progress) &&
                Objects.equals(this.courseIdId, entity.courseIdId) &&
                Objects.equals(this.courseIdName, entity.courseIdName) &&
                Objects.equals(this.studentIdId, entity.studentIdId) &&
                Objects.equals(this.studentIdUsername, entity.studentIdUsername);
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

    public String getName() {
        return name;
    }

    public double getProgress() {
        return progress;
    }

    public Integer getStudentIdId() {
        return studentIdId;
    }

    public String getStudentIdUsername() {
        return studentIdUsername;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, progress, courseIdId, courseIdName, studentIdId, studentIdUsername);
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

    public void setName(String name) {
        this.name = name;
    }

    public void setProgress(double progress) {
        this.progress = progress;
    }

    public void setStudentIdId(Integer studentIdId) {
        this.studentIdId = studentIdId;
    }

    public void setStudentIdUsername(String studentIdUsername) {
        this.studentIdUsername = studentIdUsername;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "progress = " + progress + ", " +
                "courseIdId = " + courseIdId + ", " +
                "courseIdName = " + courseIdName + ", " +
                "studentIdId = " + studentIdId + ", " +
                "studentIdUsername = " + studentIdUsername + ")";
    }
}