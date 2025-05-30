package com.ou.dto;

import java.util.Set;

public class CartDto {

    private String name;
    private Set<Integer> courseIds;
    private String studentId;
    private String currency = "USD";

    public CartDto() {
    }
    public CartDto(Set<Integer> courseIds, String studentId) {
        this.courseIds = courseIds;
        this.studentId = studentId;
        this.name = studentId+"Cart";
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Set<Integer> getCourseIds() {
        return courseIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCourseIds(Set<Integer> courseIds) {
        this.courseIds = courseIds;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}