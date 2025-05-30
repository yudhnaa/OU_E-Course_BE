package com.ou.controllers.restController;

import com.ou.dto.CourseDto;
import com.ou.dto.CourseRateDto;
import com.ou.dto.PaginationDto;
import com.ou.helpers.PaginationHelper;
import com.ou.mappers.CourseMapper;
import com.ou.mappers.CourseRateMapper;
import com.ou.pojo.Course;
import com.ou.pojo.CourseRate;
import com.ou.pojo.CourseStudent;
import com.ou.pojo.CustomUserDetails;
import com.ou.services.CourseRateService;
import com.ou.services.CourseService;
import com.ou.services.CourseStudentService;
import com.ou.utils.Pagination;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class RestCourseController {

    private final PaginationHelper paginationHelper;
    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final CourseRateService courseRateService;
    private final CourseRateMapper courseRateMapper;
    private final CourseStudentService courseStudentService;

    public RestCourseController(PaginationHelper paginationHelper, CourseService courseService, CourseMapper courseMapper, CourseRateService courseRateService, CourseRateMapper courseRateMapper, CourseStudentService courseStudentService) {
        this.paginationHelper = paginationHelper;
        this.courseService = courseService;
        this.courseMapper = courseMapper;
        this.courseRateService = courseRateService;
        this.courseRateMapper = courseRateMapper;
        this.courseStudentService = courseStudentService;
    }

    @GetMapping("/course-list")
    public ResponseEntity<Map<String, Object>> getCourseList(
            @RequestParam Map<String, String> params
            ) {

        Map<String, String> filters = new HashMap<>();

        if (params.containsKey("search")) {
            filters.put("name", params.get("search"));
        }

        if (params.containsKey("page")) {
            filters.put("page", params.get("page"));
        }

        if (params.containsKey("limit")) {
            filters.put("pageSize", params.get("limit"));
        }

        if (params.containsKey("category")) {
            filters.put("category", params.get("category"));
        }

        if (params.containsKey("lecturer")) {
            filters.put("lecturerName", params.get("lecturer"));
        }

        List<Course> courses = courseService.getCourses(filters);

        Pagination pagination = paginationHelper.getPagination(params, courseService.countSearchResults(filters));
        PaginationDto paginationDto = new PaginationDto(
                pagination.getCurrentPage(),
                pagination.getPageSize(),
                pagination.getTotalItems(),
                pagination.getTotalPages()

        );

        List<CourseDto> courseDtos = courses.stream().map(courseMapper::toDto).toList();

        Map<String, Object> response = new HashMap<>();
        response.put("courses", courseDtos);
        response.put("pagination", paginationDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/course/{id}")
    public ResponseEntity<Map<String, Object>> getCourseById(
            @PathVariable("id") Integer id
    ) {
        Optional<Course> course = courseService.getCourseById(id);

        if (course.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<CourseRateDto> courseRates = courseRateService.getCourseRatesByCourse(course.get().getId(), null)
                .stream().map(courseRateMapper::toDto).toList();


        Map<String, Object> response = new HashMap<>();
        response.put("course", courseMapper.toDto(course.get()));
        response.put("courseRates", courseRates);


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/secure/course/{courseId}/is-enrolled-course")
    public ResponseEntity<Boolean> isEnrolledCourse(
            @PathVariable("courseId") Integer courseId,
            @AuthenticationPrincipal CustomUserDetails principal) {



        Optional<CourseStudent> courseStudent = courseStudentService.getCourseStudentByCourseAndUser(courseId, principal.getUser().getId());
        boolean isEnrolled = courseStudent.isPresent() && courseStudent.get().getStudentId().getUserId().getId().equals(principal.getUser().getId());

        return new ResponseEntity<>(isEnrolled, HttpStatus.OK);
    }
}
