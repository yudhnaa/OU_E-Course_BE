package com.ou.controllers.webController.adminController;
import com.ou.dto.TestDto;
import com.ou.helpers.PaginationHelper;
import com.ou.pojo.*;
import com.ou.services.*;
import com.ou.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.ou.configs.WebApplicationSettings.PAGE_SIZE;

@RequestMapping("/course/{courseId}/tests")
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


    @GetMapping
    public String getAllTestsByCourse(@PathVariable("courseId") Integer courseId, Model model,
                                      @RequestParam Map<String,String> params) {
        long totalTests = testService.countTestsInCourse(courseId);
        if (totalTests == 0) {
            model.addAttribute("message", "No tests found for this course.");
            return "dashboard/lecturer/test/test";
        }

        Pagination pagination = paginationHelper.getPagination(params, totalTests);

        List<Test> tests = testService.getTestsByCourse(courseId, params);

        if (tests.isEmpty()) {
            model.addAttribute("message", "No tests were found");
            return "dashboard/lecturer/test/test";
        }

        model.addAttribute("tests", tests);
        model.addAttribute("courseId", courseId);
        model.addAttribute("currentPage", pagination.getCurrentPage());
//        model.addAttribute("totalTests", totalTests);
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());
        return "dashboard/lecturer/test/test";
    }

    @GetMapping("/test/{id}")
    public String getTestById(Model model,
                              @PathVariable("courseId") Integer courseId,
                              @PathVariable("id") Integer id) {
        Test test = testService.getTestById(id)
                .orElseThrow(() -> new RuntimeException("Test not found with id " + id));
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

    // Thêm endpoint để xử lý việc thêm multiple questions
    @PostMapping("/test/{testId}/questions/add-multiple")
    @ResponseBody
    public ResponseEntity<?> addMultipleQuestions(
            @PathVariable("courseId") Integer courseId,
            @PathVariable("testId") Integer testId,
            @RequestBody Map<String, List<Integer>> requestBody) {

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
            @PathVariable("questionId") Integer questionId) {

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
                             RedirectAttributes redirectAttributes,
                             Model model){
        if (result.hasErrors()) {
            test.setCourseId(new Course(courseId));
            model.addAttribute("error", "Form has errors.");
            model.addAttribute("test", test);
            return "dashboard/lecturer/test/testDetail";
        }

        try {
            Test existingTest = testService.getTestById(id)
                    .orElseThrow(() -> new RuntimeException("Test not found with id " + id));
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

        return "redirect:/course/" + courseId + "/tests/test/" + id;
    }



    @GetMapping("/add")
    public String addTestForm(@PathVariable("courseId") Integer courseId,
                              @RequestParam(value = "lecturerId", required = false, defaultValue = "1") Integer lecturerId,
                              Model model) {
        Test test = new Test();
        test.setCourseId(new Course(courseId));
        test.setCreatedByUserId(new Lecturer(lecturerId));
        test.setCreatedAt(new Date()); // Set current date as creation date
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
                                Model model) {
        if (result.hasErrors()) {
            model.addAttribute("error", "Form has errors.");
            model.addAttribute("test", test);
            return "dashboard/lecturer/test/testAdd";
        }

        try {
            if (test.getCreatedAt() == null) {
                test.setCreatedAt(new Date());
            }
            testService.addTest(test);
            redirectAttributes.addFlashAttribute("success", "Test added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding test: " + e.getMessage());
            return "dashboard/lecturer/test/testAdd";
        }

        return "redirect:/course/" + courseId + "/tests";
    }

    @PostMapping("/test/{id}/delete")
    public String deleteTest(
            @PathVariable("courseId") Integer courseId,
            @PathVariable("id") Integer id,
            RedirectAttributes redirectAttributes) {
        Optional<Test> testOpt = testService.getTestById(id);
        if (testOpt.isPresent()) {
            testService.deleteTest(id);
            redirectAttributes.addFlashAttribute("success", "Test deleted successfully!");
        } else {
            redirectAttributes.addFlashAttribute("error", "Test not found!");
        }
        return "redirect:/course/" + courseId + "/tests";
    }
}
