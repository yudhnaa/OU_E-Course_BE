package com.ou.controllers.webController.adminController;

import com.ou.dto.CourseRevenueStatisticsDto;
import com.ou.dto.RevenueStatisticsDto;
import com.ou.dto.UserRoleStatisticsDto;
import com.ou.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @GetMapping
    public String getStatisticsPage(
            @RequestParam(value = "year", required = false) Integer selectedYear,
            Model model) {

        // Lấy năm hiện tại nếu không có tham số year
        int currentYear = LocalDate.now().getYear();
        if (selectedYear == null) {
            selectedYear = currentYear;
        }

        // Tạo danh sách 10 năm gần nhất
        List<Integer> availableYears = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            availableYears.add(currentYear - i);
        }

        // Lấy dữ liệu thống kê
        List<UserRoleStatisticsDto> userRoleStatistics = statisticsService.getUserRoleStatistics();
        List<RevenueStatisticsDto> monthlyRevenueStatistics = statisticsService.getMonthlyRevenueStatistics(selectedYear);
        List<CourseRevenueStatisticsDto> courseRevenueStatistics = statisticsService.getCourseRevenueStatistics();



        // Thêm vào model
        model.addAttribute("userRoleStats", userRoleStatistics);
        model.addAttribute("monthlyRevenueStats", monthlyRevenueStatistics);
        model.addAttribute("courseRevenueStats", courseRevenueStatistics);
        model.addAttribute("availableYears", availableYears);
        model.addAttribute("selectedYear", selectedYear);
        model.addAttribute("currentYear", currentYear);

        return "dashboard/admin/statistics/statistics";
    }
}