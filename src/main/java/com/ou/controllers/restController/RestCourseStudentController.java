package com.ou.controllers.restController;

import com.ou.dto.CourseDto;
import com.ou.dto.CourseWithProgressDto;
import com.ou.dto.PaginationDto;
import com.ou.helpers.PaginationHelper;
import com.ou.mappers.CourseMapper;
import com.ou.pojo.Course;
import com.ou.pojo.CourseStudent;
import com.ou.pojo.CustomUserDetails;
import com.ou.pojo.Student;
import com.ou.services.CourseService;
import com.ou.services.CourseStudentService;
import com.ou.services.LessonStudentService;
import com.ou.services.StudentService;
import com.ou.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private CourseStudentService courseStudentService;
    @Autowired
    private LessonStudentService lessonStudentService;

    @GetMapping("/enrolled-courses")
    public ResponseEntity<Map<String, Object>> getEnrolledCourses(
            @RequestParam Map<String, String> params,
            @AuthenticationPrincipal CustomUserDetails principal) {

        Map<String, String> filters = paginationHelper.extractFilters(
                params, "search", "page", "limit", "category", "lecturer"
        );

        Student student = studentService.getStudentByUserId(principal.getUser().getId());

        List<Object[]> courses = courseService.getCoursesWithProgressByStudentId(student.getId(), filters);

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

//      Mark a lesson as learned and update course progress
    @PostMapping("/courses/{courseId}/lessons/{lessonId}/mark-learned")
    public ResponseEntity<Map<String, Object>> markLessonAsLearned(
            @PathVariable("courseId") Integer courseId,
            @PathVariable("lessonId") Integer lessonId,
            @AuthenticationPrincipal CustomUserDetails principal) {

        try {
            Student student = studentService.getStudentByUserId(principal.getUser().getId());

            // Mark lesson as learned
            lessonStudentService.markLessonAsLearned(lessonId, student.getId());

            // Update course progress
            CourseStudent updatedCourseStudent = courseStudentService.updateCourseProgress(courseId, student.getId());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("progress", updatedCourseStudent.getProgress());
            response.put("message", "Lesson marked as learned and progress updated");

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Failed to mark lesson as learned: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

//    Get learning progress for a specific course
    @GetMapping("/courses/{courseId}/progress")
    public ResponseEntity<Map<String, Object>> getCourseProgress(
            @PathVariable("courseId") Integer courseId,
            @AuthenticationPrincipal CustomUserDetails principal) {

        try {
            Student student = studentService.getStudentByUserId(principal.getUser().getId());

            // Calculate current progress
            double progress = courseStudentService.calculateCourseProgress(courseId, student.getId());

            // Get course-student record
            var courseStudentOpt = courseStudentService.getCourseStudentByCourseAndStudent(courseId, student.getId());

            if (courseStudentOpt.isEmpty()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("error", "Student is not enrolled in this course");
                return ResponseEntity.badRequest().body(errorResponse);
            }

            CourseStudent courseStudent = courseStudentOpt.get();

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("courseId", courseId);
            response.put("studentId", student.getId());
            response.put("progress", progress);
            response.put("storedProgress", courseStudent.getProgress());
            response.put("isCompleted", progress >= 1.0);
            response.put("shouldGenerateCertificate", courseStudentService.shouldGenerateCertificate(courseStudent));

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Failed to get course progress: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


//    Update course progress manually (recalculate and sync)
    @PostMapping("/courses/{courseId}/progress/update")
    public ResponseEntity<Map<String, Object>> updateCourseProgress(
            @PathVariable("courseId") Integer courseId,
            @AuthenticationPrincipal CustomUserDetails principal) {

        try {
            Student student = studentService.getStudentByUserId(principal.getUser().getId());

            // Update and recalculate progress
            CourseStudent updatedCourseStudent = courseStudentService.updateCourseProgress(courseId, student.getId());

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("courseId", courseId);
            response.put("studentId", student.getId());
            response.put("progress", updatedCourseStudent.getProgress());
            response.put("isCompleted", updatedCourseStudent.getProgress() >= 1.0);
            response.put("message", "Course progress updated successfully");

            return ResponseEntity.ok(response);

        } catch (IllegalArgumentException e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Failed to update course progress: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
