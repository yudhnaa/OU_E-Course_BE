package com.ou.controllers.webController.adminController;

import com.ou.exceptions.NotFoundException;
import com.ou.formBean.CourseLecturerForm;
import com.ou.formBean.CourseStudentForm;
import com.ou.helpers.PaginationHelper;
import com.ou.mappers.LecturerMapper;
import com.ou.pojo.*;
import com.ou.services.*;
import com.ou.utils.Pagination;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private CourseLecturerService courseLecturerService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private LecturerMapper lecturerMapper;


    @GetMapping("/courses")
    public String index(
            Model model,
            @RequestParam Map<String, String> params,
            RedirectAttributes redirectAttributes
    ) {

        long totalItems;
        if (params.get("name") != null) {
            totalItems = courseService.countSearchResults(params);
        }
        else
            totalItems = courseService.countCourses();

        Pagination pagination = paginationHelper.getPagination(params, totalItems);

        params.put("page", String.valueOf(pagination.getCurrentPage()));

        List<Course> courses = courseService.getCourses(params);

        if (courses.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg_error", "No courses found.");
            return "redirect:/admin/courses";
        }

        model.addAttribute("courses", courses);

        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages() > 0 ? pagination.getTotalPages() : 1);
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("filterName", params.getOrDefault("name", " "));

        return "dashboard/admin/course/course_list";
    }

    @GetMapping("/course/{id}")
    public String courseDetail(Model model,
                               @PathVariable("id") int id,
                               @RequestParam Map<String, String> params
    ) throws Exception {

        Course course = courseService.getCourseById(id)
                .orElseThrow(() -> new NotFoundException(localizationService.getMessage("course.notFound", LocaleContextHolder.getLocale())));

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

        List<CourseLecturer> courseLecturers = courseLecturerService.getCourseLecturersByCourse(course.getId(), params);
        CourseLecturerForm courseLecturerForm = new CourseLecturerForm();
        courseLecturerForm.setCourseLecturers(courseLecturers);

        courseLecturerForm.setLecturers(lecturerService.getLecturers(null).stream()
                .map(lecturer ->
                        lecturerMapper.toDto(lecturer)).collect(Collectors.toList()));

        courseLecturerForm.setCountCurrentLecturers(lecturerService.countLecturersByCourse(course.getId()));

        model.addAttribute("totalItems", totalCourseStudents);
        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages() > 0 ? pagination.getTotalPages() : 1);
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());

        model.addAttribute("categories", categories);
        model.addAttribute("course", course);
        model.addAttribute("courseStudents", courseStudents);

        model.addAttribute("courseRates", courseRates);

        model.addAttribute("msg_error", params.getOrDefault("msg_error", null));

        model.addAttribute("filterName", searchName.isEmpty() ? null : searchName);

        model.addAttribute("courseLecturerForm", courseLecturerForm);


        return "dashboard/admin/course/course_detail";
    }


    @PostMapping("/course/{id}")
    public String updateCourse(Model model,
                               @PathVariable("id") int id,
                               @ModelAttribute("course") Course course,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes
    ) throws Exception {

        // Validate course data
        if (bindingResult.hasErrors()) {
            String msg_error = bindingResult.getFieldError().getDefaultMessage();
            redirectAttributes.addAttribute("msg_error", msg_error);
            return "redirect:/admin/course/" + id;
        }

        Course updateCourse = courseService.updateCourse(course);
        if (updateCourse != null) {
            redirectAttributes.addFlashAttribute("msg_success", "Course updated successfully.");
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Failed to update course.");
        }

        return "redirect:/admin/course/" + id;
    }

    @PostMapping("/course/{courseId}/updateLecture")
    public String updateCourseLecturer(
            @PathVariable("courseId") int courseId,
            @ModelAttribute CourseLecturerForm form,
            RedirectAttributes redirectAttributes
    ) throws Exception {

        if (form.getCourseLecturers() == null){
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("courseLecturer.update.error", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course/" + courseId + "#course-details";
        }

        Boolean isSuccess =  courseLecturerService.updateCourseLecturer(form.getCourseLecturers());

        if (!isSuccess)
            redirectAttributes.addFlashAttribute("msg_error", "Failed to add course lecturers.");
        else
            redirectAttributes.addFlashAttribute("msg_success", "Course lecturers added successfully.");

        return "redirect:/admin/course/" + courseId + "#course-details";
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

    @GetMapping("/course/create")
    public String createCourseView(Model model) {
        List<Category> categories = categoryService.getCategories(null);

        Course course = new Course();
        course.setDateAdded(LocalDateTime.now());

        model.addAttribute("course", course);
        model.addAttribute("categories", categories);

        return "dashboard/admin/course/course_create";
    }

    @PostMapping("/course/create")
    public String createCourse(
            Model model,
            @Valid @ModelAttribute("course") Course course,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) throws IOException {
        // Validate course data
        if (bindingResult.hasErrors()) {
            String msg_error = bindingResult.getFieldError().getDefaultMessage();
            redirectAttributes.addAttribute("msg_error", msg_error);
            return "redirect:/admin/course/create";
        }

        // Save the course
        Course newCourse =  courseService.addCourse(course);

        if (newCourse != null) {
            redirectAttributes.addFlashAttribute("msg_success", "Course created successfully.");
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Failed to create course.");
        }

        return "redirect:/admin/course/" + newCourse.getId();
    }
}
