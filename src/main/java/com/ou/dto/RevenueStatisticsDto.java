package com.ou.dto;

import java.math.BigDecimal;

public class RevenueStatisticsDto {
    private int year;
    private int month;
    private BigDecimal totalRevenue;
    private Long totalOrders;

    public RevenueStatisticsDto() {}

    // Constructor matching actual HQL return types: Integer, Integer, BigDecimal, Long
    public RevenueStatisticsDto(Integer year, Integer month, BigDecimal totalRevenue, Long totalOrders) {
        this.year = year != null ? year : 0;
        this.month = month != null ? month : 0;
        this.totalRevenue = totalRevenue;
        this.totalOrders = totalOrders;
    }

    // Getters and Setters
    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }
    public int getMonth() { return month; }
    public void setMonth(int month) { this.month = month; }
    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
    public Long getTotalOrders() { return totalOrders; }
    public void setTotalOrders(Long totalOrders) { this.totalOrders = totalOrders; }
}