package com.ou.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ou.pojo.CourseRate;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.Objects;

/**
 * DTO for {@link CourseRate}
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CourseRateDto implements Serializable {
    private Integer id;
    private double rate;
    @Size(max = 65535)
    private String comment;
    private Integer courseIdId;
    private Integer studentIdId;
    private String studentIdUsername;

    public CourseRateDto() {
    }

    public CourseRateDto(Integer id, double rate, String comment, Integer courseIdId, Integer studentIdId, String studentIdUsername) {
        this.id = id;
        this.rate = rate;
        this.comment = comment;
        this.courseIdId = courseIdId;
        this.studentIdId = studentIdId;
        this.studentIdUsername = studentIdUsername;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CourseRateDto entity = (CourseRateDto) o;
        return Objects.equals(this.id, entity.id) &&
                Objects.equals(this.rate, entity.rate) &&
                Objects.equals(this.comment, entity.comment) &&
                Objects.equals(this.courseIdId, entity.courseIdId) &&
                Objects.equals(this.studentIdId, entity.studentIdId) &&
                Objects.equals(this.studentIdUsername, entity.studentIdUsername);
    }

    public String getComment() {
        return comment;
    }

    public Integer getCourseIdId() {
        return courseIdId;
    }

    public Integer getId() {
        return id;
    }

    public double getRate() {
        return rate;
    }

    public Integer getStudentIdId() {
        return studentIdId;
    }

    public String getStudentIdUsername() {
        return studentIdUsername;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, rate, comment, courseIdId, studentIdId, studentIdUsername);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCourseIdId(Integer courseIdId) {
        this.courseIdId = courseIdId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRate(double rate) {
        this.rate = rate;
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
                "rate = " + rate + ", " +
                "comment = " + comment + ", " +
                "courseIdId = " + courseIdId + ", " +
                "studentIdId = " + studentIdId + ", " +
                "studentIdUsername = " + studentIdUsername + ")";
    }
}