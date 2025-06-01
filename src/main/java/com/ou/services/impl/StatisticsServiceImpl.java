package com.ou.services.impl;

import com.ou.dto.CourseRevenueStatisticsDto;
import com.ou.dto.RevenueStatisticsDto;
import com.ou.dto.UserRoleStatisticsDto;
import com.ou.repositories.StatisticsRepository;
import com.ou.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsRepository statisticsRepository;

    @Override
    public List<UserRoleStatisticsDto> getUserRoleStatistics() {
        return statisticsRepository.getUserRoleStatistics();
    }

    @Override
    public List<RevenueStatisticsDto> getMonthlyRevenueStatistics(int year) {
        return statisticsRepository.getMonthlyRevenueStatistics(year);
    }

    @Override
    public List<RevenueStatisticsDto> getYearlyRevenue() {
        return statisticsRepository.getYearlyRevenue();
    }

    @Override
    public List<CourseRevenueStatisticsDto> getCourseRevenueStatistics() {
        return statisticsRepository.getCourseRevenueStatistics();
    }

    @Override
    public List<RevenueStatisticsDto> getLast10YearsRevenueStatistics() {
        return statisticsRepository.getLast10YearsRevenueStatistics();
    }
}
