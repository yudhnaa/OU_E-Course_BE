package com.ou.controllers.restController;

import com.ou.dto.CourseDto;
import com.ou.dto.CourseWithProgressDto;
import com.ou.dto.PaginationDto;
import com.ou.helpers.PaginationHelper;
import com.ou.mappers.CourseMapper;
import com.ou.pojo.Course;
import com.ou.pojo.CustomUserDetails;
import com.ou.pojo.Student;
import com.ou.services.CourseService;
import com.ou.services.StudentService;
import com.ou.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/secure")
public class RestCourseStudentController {

    @Autowired
    private CourseService courseService;
    @Autowired
    private PaginationHelper paginationHelper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private StudentService studentService;

    @GetMapping("/enrolled-courses")
    public ResponseEntity<Map<String, Object>> getEnrolledCourses(
            @RequestParam Map<String, String> params,
            @AuthenticationPrincipal CustomUserDetails principal) {

        Map<String, String> filters = paginationHelper.extractFilters(
                params, "search", "page", "limit", "category", "lecturer"
        );

        Student student = studentService.getStudentByUserId(principal.getUser().getId());

        List<Object[]> courses = courseService.getCoursesWithProgressByStudentId(student.getId(), filters);

//        List<CourseWithProgressDto> courseDtos = courses.stream().map(courseMapper::toDtoWithProgress).toList();


        List<CourseWithProgressDto> courseDtos = courses.stream().map(row -> {
            Course course = (Course) row[0];
            Double progress = (Double) row[1];
            return courseMapper.toDtoWithProgress(course, progress);
        }).toList();


        // Count total items for pagination
        long totalItems = courseService.countCoursesByStudentId(student.getId(), filters);
        Pagination pagination = paginationHelper.getPagination(params, totalItems);
        PaginationDto paginationDto = new PaginationDto(
                pagination.getCurrentPage(),
                pagination.getPageSize(),
                pagination.getTotalItems(),
                pagination.getTotalPages()

        );

        Map<String, Object> response = new HashMap<>();
        response.put("courses", courseDtos);
        response.put("pagination", paginationDto);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
