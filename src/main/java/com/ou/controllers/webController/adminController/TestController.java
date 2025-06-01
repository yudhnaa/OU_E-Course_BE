package com.ou.controllers.webController.adminController;
import com.ou.dto.TestDto;
import com.ou.exceptions.NotFoundException;
import com.ou.helpers.PaginationHelper;
import com.ou.pojo.*;
import com.ou.services.*;
import com.ou.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static com.ou.configs.WebApplicationSettings.PAGE_SIZE;

@RequestMapping("/admin/courses/{courseId}/tests")
@Controller
public class TestController {
    @Autowired
    private TestService testService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private PaginationHelper paginationHelper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private TestQuestionService testQuestionService;

    @Autowired
    private LocalizationService localizationService;


    @GetMapping
    public String getAllTestsByCourse(@PathVariable("courseId") Integer courseId,
                                      Model model,
                                      @RequestParam Map<String,String> params,
                                      @AuthenticationPrincipal CustomUserDetails principal,
                                      RedirectAttributes redirectAttributes) {
        Course course = courseService.getCourseByIdWithPermissionCheck(courseId, principal.getUser());
        params.put("courseId", String.valueOf(courseId));

        if(principal.getUser().getUserRoleId().getName().contains("LECTURER")) {
            Lecturer lecturer = lecturerService.getLecturerByUserId(principal.getUser().getId())
                    .orElseThrow(() -> new NotFoundException(localizationService.getMessage("lecturer.notFound", LocaleContextHolder.getLocale())));
            params.put("createdByUserId", String.valueOf(lecturer.getId()));
        }

        long totalTests = testService.countSearchResults(params);
        List<Test> tests = testService.getAllTests(params);

        // Tạo pagination ngay cả khi không có test
        Pagination pagination = paginationHelper.getPagination(params, totalTests);

        // Thêm thông báo nếu không có test
        if (totalTests == 0) {
            model.addAttribute("msg_info", "No tests found for this course. Create your first test!");
        }

        model.addAttribute("tests", tests);
        model.addAttribute("courseId", courseId);
        model.addAttribute("course", course);
        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());

        // Luôn return về trang test thay vì redirect
        return "dashboard/lecturer/test/test";
    }

    @GetMapping("/test/{id}")
    public String getTestById(Model model,
                              @PathVariable("courseId") Integer courseId,
                              @PathVariable("id") Integer id,
                              @AuthenticationPrincipal CustomUserDetails principal) {
        Test test = testService.getTestByIdWithPermissionsCheck(id, principal.getUser());
        List<Question> questions = questionService.getQuestionsByTest(id);
        List<Question> allQuestionsInCourse = questionService.getQuestionsByCourse(courseId);

        // Tạo Set để kiểm tra câu hỏi đã tồn tại
        Set<Integer> existingQuestionIds = questions.stream()
                .map(Question::getId)
                .collect(Collectors.toSet());
        List<Exercise> exercises = exerciseService.getExercisesByCourse(courseId,null);

        test.setCourseId(new Course(courseId));
        model.addAttribute("test", test);
        model.addAttribute("courseId", courseId);
        model.addAttribute("questions", questions);
        model.addAttribute("allQuestionsInCourse", allQuestionsInCourse);
        model.addAttribute("existingQuestionIds", existingQuestionIds);
        model.addAttribute("exercises", exercises);
        return "dashboard/lecturer/test/testDetail";
    }

    // endpoint để xử lý việc thêm multiple questions
    @PostMapping("/test/{testId}/questions/add-multiple")
    @ResponseBody
    public ResponseEntity<?> addMultipleQuestions(
            @PathVariable("courseId") Integer courseId,
            @PathVariable("testId") Integer testId,
            @RequestBody Map<String, List<Integer>> requestBody,
            @AuthenticationPrincipal CustomUserDetails principal) {
        // Kiểm tra quyền của người dùng
        Test test = testService.getTestByIdWithPermissionsCheck(testId, principal.getUser());
        if (test == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("success", false, "message", "Test not found or access denied"));
        }
        try {
            List<Integer> questionIds = requestBody.get("questionIds");

            if (questionIds == null || questionIds.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("success", false, "message", "No question IDs provided"));
            }

            for (Integer questionId : questionIds) {
                testQuestionService.addQuestionToTest(testId, questionId);
            }

            return ResponseEntity.ok(Map.of("success", true, "message", "Questions added successfully!"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "Error adding questions: " + e.getMessage()));
        }
    }

    // Thêm endpoint để xóa câu hỏi khỏi test
    @PostMapping("/test/{testId}/question/{questionId}/delete")
    @ResponseBody
    public ResponseEntity<?> deleteQuestionFromTest(
            @PathVariable("courseId") Integer courseId,
            @PathVariable("testId") Integer testId,
            @PathVariable("questionId") Integer questionId,
            @AuthenticationPrincipal CustomUserDetails principal) {
        Test test = testService.getTestByIdWithPermissionsCheck(testId, principal.getUser());
        if (test == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("success", false, "message", "Test not found or access denied"));
        }
        try {
            boolean removed = testQuestionService.removeQuestionFromTest(testId, questionId);
            if (removed) {
                return ResponseEntity.ok(Map.of("success", true, "message", "Question removed successfully"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("success", false, "message", "Question not found in this test"));
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("success", false, "message", "Error removing question: " + e.getMessage()));
        }
    }


    @PostMapping("/test/{id}")
    public String updateTest(@PathVariable("courseId") Integer courseId,
                             @PathVariable("id") Integer id,
                             @ModelAttribute("test") Test test,
                             BindingResult result,
                             @AuthenticationPrincipal CustomUserDetails principal,
                             RedirectAttributes redirectAttributes,
                             Model model){
        if (result.hasErrors()) {
            test.setCourseId(new Course(courseId));
            model.addAttribute("error", "Form has errors.");
            model.addAttribute("test", test);
            return "dashboard/lecturer/test/testDetail";
        }

        try {
            Test existingTest = testService.getTestByIdWithPermissionsCheck(id, principal.getUser());
            if (existingTest == null) {
                throw new NotFoundException(localizationService.getMessage("test.notFound", LocaleContextHolder.getLocale()));
            }
            existingTest.setName(test.getName());
            existingTest.setDescription(test.getDescription());
            existingTest.setDurationMinutes(test.getDurationMinutes());
            existingTest.setMaxScore(test.getMaxScore());
            if (test.getCourseId() != null) {
                existingTest.setCourseId(test.getCourseId());
            }
            testService.updateTest(existingTest);
            redirectAttributes.addFlashAttribute("success", "Test updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating test: " + e.getMessage());
        }

        return "redirect:/admin/courses/" + courseId + "/tests/test/" + id;
    }



    @GetMapping("/add")
    public String addTestForm(@PathVariable("courseId") Integer courseId,
                              @AuthenticationPrincipal CustomUserDetails principal,
                              Model model) {
        // Kiểm tra quyền của người dùng
        Course course = courseService.getCourseByIdWithPermissionCheck(courseId, principal.getUser());
        if (course == null) {
            throw new NotFoundException(localizationService.getMessage("course.notFound", LocaleContextHolder.getLocale()));
        }
        Lecturer lecturer = lecturerService.getLecturerByUserId(principal.getUser().getId())
                .orElseThrow(() -> new NotFoundException(localizationService.getMessage("lecturer.notFound", LocaleContextHolder.getLocale())));

        Test test = new Test();
        test.setCourseId(course);
        test.setCreatedByUserId(lecturer);
        test.setCreatedAt(LocalDateTime.now()); // Set current date as creation date
        test.setMaxScore(new BigDecimal(100)); // Default max score
        test.setDurationMinutes(60); // Default duration

        model.addAttribute("test", test);
        model.addAttribute("courseId", courseId);
        return "dashboard/lecturer/test/testAdd";
    }

    @PostMapping("/add")
    public String addTestSubmit(@PathVariable("courseId") Integer courseId,
                                @ModelAttribute("test") Test test,
                                BindingResult result,
                                RedirectAttributes redirectAttributes,
                                @AuthenticationPrincipal CustomUserDetails principal,
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Form has errors.");
            model.addAttribute("test", test);
            return "dashboard/lecturer/test/testAdd";
        }

        // Kiểm tra quyền của người dùng
        Course course = courseService.getCourseByIdWithPermissionCheck(courseId, principal.getUser());
        if (course == null) {
            throw new NotFoundException(localizationService.getMessage("course.notFound", LocaleContextHolder.getLocale()));
        }
        try {
            if (test.getCreatedAt() == null) {
                test.setCreatedAt(LocalDateTime.now());
            }
            testService.addTest(test);
            redirectAttributes.addFlashAttribute("success", "Test added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding test: " + e.getMessage());
            return "dashboard/lecturer/test/testAdd";
        }

        return "redirect:/admin/courses/" + courseId + "/tests" + "/test/" + test.getId();
    }

    @PostMapping("/test/{id}/delete")
    public String deleteTest(
            @PathVariable("courseId") Integer courseId,
            @PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal CustomUserDetails principal) {
        Test test = testService.getTestByIdWithPermissionsCheck(id, principal.getUser());
        if (test != null) {
            testService.deleteTest(id);
            redirectAttributes.addFlashAttribute("success", "Test deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Test not found!");
        }
        return "redirect:/admin/courses/" + courseId + "/tests";
    }
}
