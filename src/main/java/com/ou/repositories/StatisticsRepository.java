package com.ou.repositories;

import com.ou.dto.CourseRevenueStatisticsDto;
import com.ou.dto.RevenueStatisticsDto;
import com.ou.dto.UserRoleStatisticsDto;

import java.util.List;

public interface StatisticsRepository {
    List<UserRoleStatisticsDto> getUserRoleStatistics();
    List<RevenueStatisticsDto> getMonthlyRevenueStatistics(int year);
    List<RevenueStatisticsDto> getYearlyRevenue();
    List<CourseRevenueStatisticsDto> getCourseRevenueStatistics();
    List<RevenueStatisticsDto> getLast10YearsRevenueStatistics();
}
