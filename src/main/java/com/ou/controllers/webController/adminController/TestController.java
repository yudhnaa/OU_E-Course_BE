package com.ou.controllers.webController.adminController;
import com.ou.dto.TestDto;
import com.ou.helpers.PaginationHelper;
import com.ou.pojo.Course;
import com.ou.pojo.Lecturer;
import com.ou.pojo.Question;
import com.ou.pojo.Test;
import com.ou.services.CourseService;
import com.ou.services.LecturerService;
import com.ou.services.QuestionService;
import com.ou.services.TestService;
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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Register custom editor for Course objects
        binder.registerCustomEditor(Course.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    Course course = courseService.getCourseById(Integer.parseInt(text))
                            .orElseThrow(() -> new IllegalArgumentException("Invalid course ID: " + text));
                    setValue(course);
                }
            }

            @Override
            public String getAsText() {
                Course course = (Course) getValue();
                return (course != null ? course.getId().toString() : "");
            }
        });

        binder.registerCustomEditor(Lecturer.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                if (text == null || text.isEmpty()) {
                    setValue(null);
                } else {
                    Lecturer lecturer = lecturerService.getLecturerById(Integer.parseInt(text))
                            .orElseThrow(() -> new IllegalArgumentException("Invalid lecturer ID: " + text));
                    setValue(lecturer);
                }
            }

            @Override
            public String getAsText() {
                Lecturer lecturer = (Lecturer) getValue();
                return (lecturer != null ? lecturer.getId().toString() : "");
            }
        });
    }

    @GetMapping
    public String getAllTestsByCourse(@PathVariable("courseId") Integer courseId, Model model,@RequestParam Map<String,String> params) {
        long totalTests = testService.countTestsInCourse(courseId);

        if (totalTests == 0) {
            model.addAttribute("message", "No tests found for this course.");
            return "dashboard/lecturer/test";
        }

        Pagination pagination = paginationHelper.getPagination(params, totalTests);

        List<Test> tests = testService.getTestsByCourse(courseId, params);
        model.addAttribute("tests", tests);
        model.addAttribute("courseId", courseId);
        model.addAttribute("currentPage", pagination.getCurrentPage());
//        model.addAttribute("totalTests", totalTests);
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());
        return "dashboard/lecturer/test";
    }

    @GetMapping("/test/{id}")
    public String getTestById(Model model,
                              @PathVariable("courseId") Integer courseId,
                              @PathVariable("id") Integer id) {
        Test test = testService.getTestById(id)
                .orElseThrow(() -> new RuntimeException("Test not found with id " + id));

        test.setCourseId(new Course(courseId));
        model.addAttribute("test", test);
        return "dashboard/lecturer/testDetail";
    }


    @PostMapping("/test/{id}")
    public String updateTest(@PathVariable("courseId") Integer courseId,
                             @PathVariable("id") Integer id,
                             @ModelAttribute("test") Test test,
                             BindingResult result,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        if (result.hasErrors()) {
            test.setCourseId(new Course(courseId)); // 👈 Thêm dòng này để tránh lỗi bind khi trả về view
            model.addAttribute("error", "Form has errors.");
            model.addAttribute("test", test);
            return "dashboard/lecturer/testDetail";
        }

        try {
            Test existingTest = testService.getTestById(id)
                    .orElseThrow(() -> new RuntimeException("Test not found with id " + id));
            // Update information
            existingTest.setName(test.getName());
            existingTest.setDescription(test.getDescription());
            existingTest.setDurationMinutes(test.getDurationMinutes());
            existingTest.setMaxScore(test.getMaxScore());
            // The courseId should now be properly bound thanks to our custom editor
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
        return "dashboard/lecturer/testAdd";
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
            return "dashboard/lecturer/testAdd";
        }

        try {
            if (test.getCreatedAt() == null) {
                test.setCreatedAt(new Date());
            }
            testService.addTest(test);
            redirectAttributes.addFlashAttribute("success", "Test added successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding test: " + e.getMessage());
            return "dashboard/lecturer/testAdd";
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
