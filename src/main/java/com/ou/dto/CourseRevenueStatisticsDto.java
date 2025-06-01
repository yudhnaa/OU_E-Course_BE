package com.ou.dto;

import java.math.BigDecimal;

public class CourseRevenueStatisticsDto {
    private Long courseId;
    private String courseName;
    private String categoryName;
    private Double coursePrice;
    private Long enrollmentCount;
    private BigDecimal totalRevenue;

    public CourseRevenueStatisticsDto() {}

    // Constructor for Integer courseId (matching HQL query)
    public CourseRevenueStatisticsDto(Integer courseId, String courseName, String categoryName,
                                      Long enrollmentCount, BigDecimal totalRevenue) {
        this.courseId = courseId != null ? courseId.longValue() : null;
        this.courseName = courseName;
        this.categoryName = categoryName;
        this.enrollmentCount = enrollmentCount;
        this.totalRevenue = totalRevenue;
    }

    // Constructor for Long courseId
    public CourseRevenueStatisticsDto(Integer courseId, String courseName, String categoryName,
                                      Integer enrollmentCount, BigDecimal totalRevenue) {
        this.courseId = courseId != null ? courseId.longValue() : null;
        this.courseName = courseName;
        this.categoryName = categoryName;
        this.enrollmentCount = enrollmentCount != null ? enrollmentCount.longValue() : null;
        this.totalRevenue = totalRevenue;
    }

    public CourseRevenueStatisticsDto(Long courseId, String courseName, String categoryName,
                                      Double coursePrice, Long enrollmentCount, BigDecimal totalRevenue) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.categoryName = categoryName;
        this.coursePrice = coursePrice;
        this.enrollmentCount = enrollmentCount;
        this.totalRevenue = totalRevenue;
    }

    // Getters and Setters
    public Long getCourseId() { return courseId; }
    public void setCourseId(Long courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public Double getCoursePrice() { return coursePrice; }
    public void setCoursePrice(Double coursePrice) { this.coursePrice = coursePrice; }

    public Long getEnrollmentCount() { return enrollmentCount; }
    public void setEnrollmentCount(Long enrollmentCount) { this.enrollmentCount = enrollmentCount; }

    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
}