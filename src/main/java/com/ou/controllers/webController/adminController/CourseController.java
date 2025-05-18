package com.ou.controllers.webController.adminController;

import com.ou.formBean.CourseStudentForm;
import com.ou.helpers.PaginationHelper;
import com.ou.pojo.Category;
import com.ou.pojo.Course;
import com.ou.pojo.CourseRate;
import com.ou.pojo.CourseStudent;
import com.ou.services.CategoryService;
import com.ou.services.CourseRateService;
import com.ou.services.CourseService;
import com.ou.services.CourseStudentService;
import com.ou.utils.Pagination;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

import static com.ou.configs.WebApplicationSettings.PAGE_SIZE;

@RequestMapping(path = "/admin")
@Controller
public class CourseController{

    @Autowired
    private CourseService courseService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private PaginationHelper paginationHelper;

    @Autowired
    private CourseStudentService courseStudentService;

    @Autowired
    private CourseRateService courseRateService;


    @GetMapping("/courses")
    public String index(Model model, @RequestParam Map<String, String> params) {
        long totalCourses = courseService.countCourses();
        Pagination pagination = paginationHelper.getPagination(params, totalCourses);

        params.put("page", String.valueOf(pagination.getCurrentPage()));

        List<Course> courses = courseService.getCourses(params);


        model.addAttribute("courses", courses);
        model.addAttribute("totalItems", totalCourses);
        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());

        return "dashboard/admin/course/course_list";
    }

    @GetMapping("/course/{id}")
    public String courseDetail(Model model,
                               @PathVariable("id") int id,
                               @RequestParam Map<String, String> params
    ) throws Exception {

        Course course = courseService.getCourseById(id)
                .orElseThrow(() -> new Exception("Course not found"));

        List<Category> categories = categoryService.getCategories(params);

        String searchName = params.getOrDefault("name", "").trim();

        List<CourseStudent> courseStudents;
        long totalCourseStudents;

        if (!searchName.isEmpty()) {
            params.put("name", searchName);
            courseStudents = courseStudentService.searchCourseStudents(params, params);
            totalCourseStudents = courseStudentService.countSearchResults(params);
        } else {
            courseStudents = courseStudentService.getCourseStudentsByCourse(id, params);
            totalCourseStudents = courseStudentService.countCourseStudentsByCourse(id);
        }

        List<CourseRate> courseRates = courseRateService.getCourseRatesByCourse(id, params);

        Pagination pagination = paginationHelper.getPagination(params, totalCourseStudents);

        model.addAttribute("totalItems", totalCourseStudents);
        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());

        model.addAttribute("categories", categories);
        model.addAttribute("course", course);
        model.addAttribute("courseStudents", courseStudents);

        model.addAttribute("courseRates", courseRates);

        model.addAttribute("msg_error", params.getOrDefault("msg_error", null));

        model.addAttribute("filterName", searchName.isEmpty() ? null : searchName);

        return "dashboard/admin/course/course_detail";
    }


    @PostMapping("/course/{id}")
    public String updateCourse(Model model,
                               @PathVariable("id") int id,
                               @Valid @ModelAttribute("course") Course course,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes
    ) throws Exception {

        // Validate course data
        if (bindingResult.hasErrors()) {
            String msg_error = bindingResult.getFieldError().getDefaultMessage();
            redirectAttributes.addAttribute("msg_error", msg_error);
            return "redirect:/admin/course/" + id;
        }

        courseService.updateCourse(course);

        return "redirect:/admin/course/" + id;
    }

    @PostMapping("/course/{courseId}/add-members")
    public String addMembersToCourse(
            @PathVariable("courseId") int courseId,
            @ModelAttribute CourseStudentForm form
    ) throws Exception {
        for (CourseStudent item : form.getItems()) {
            courseStudentService.addCourseStudent(item);
        }
        return "redirect:/admin/course/" + courseId;
    }

    @PostMapping("/course/{id}/delete")
    public String deleteCourse(@PathVariable("id") int id,
                                RedirectAttributes redirectAttributes) {
        boolean isDeleted = courseService.deleteCourse(id);
        if (isDeleted) {
            redirectAttributes.addFlashAttribute("msg_success", "Course deleted successfully.");
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Failed to delete course.");
        }
        return "redirect:/admin/courses";
    }
}
