package com.ou.controllers.webController.adminController;


import com.ou.dto.LessonDto;
import com.ou.helpers.PaginationHelper;
import com.ou.mappers.LessonMapper;
import com.ou.pojo.*;
import com.ou.services.*;
import com.ou.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private CourseService courseService;

    @Autowired
    LocalizationService localizationService;

    @Autowired
    LessonTypeService lessonTypeService;

    @Autowired
    private UserService userService;
    @Autowired
    private LessonAttachmentService lessonAttachmentService;
    @Autowired
    private LessonMapper lessonMapper;
    @Autowired
    private PaginationHelper paginationHelper;


    @GetMapping("/course/{courseId}/lessons")
    public String getLessonsView(
            Model model,
            @PathVariable("courseId") Integer courseId,
            @RequestParam Map<String, String> params,
            RedirectAttributes redirectAttributes
    ) {

        // Get the course by ID
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isEmpty()) {
            // If the course is not found, redirect to the course list with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("course.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/courses";
        }

        // pagination
        long total = lessonService.countLessonsByCourse(course.get().getId());
        Pagination pagination = paginationHelper.getPagination(params, total);
        params.put("page", String.valueOf(pagination.getCurrentPage()));

        // Get the lessons for the course
        List<Lesson> lessons = lessonService.getLessonsByCourse(course.get().getId(), params);
        if (lessons.isEmpty()) {
            // If no lessons are found, redirect to the course list with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lesson.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/courses";
        }
        List<LessonDto> lessonDtos = lessons.stream().map(l -> lessonMapper.toDto(l)).collect(Collectors.toList());

        // Add the lessons and course to the model
        model.addAttribute("lessons", lessonDtos);
        model.addAttribute("course", course.get());

        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());
        model.addAttribute("totalItems", total);

        return "dashboard/admin/lesson/lesson_list";
    }

    @GetMapping("/course/{courseId}/lesson/create")
    public String createLessonView(
            Model model,
            @PathVariable("courseId") Integer courseId,
            RedirectAttributes redirectAttributes
    ) {

        // Get the course by ID
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isEmpty()) {
            // If the course is not found, redirect to the course list with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("course.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course";
        }

        Lesson lesson = new Lesson();
        lesson.setCourseId(course.get());

        List<LessonType> lessonTypes = lessonTypeService.getLessonTypes();

        //Mock login user
        User u = userService.getUserById(2);
        model.addAttribute("authUser", u);

        // Add the lesson and course to the model
        model.addAttribute("lesson", lesson);
        model.addAttribute("lessonTypes", lessonTypes);
        model.addAttribute("course", course.get());

        return "dashboard/admin/lesson/lesson_create";
    }

    @PostMapping("/course/{courseId}/lesson/create")
    public String createLesson(
            Model model,
            @PathVariable("courseId") Integer courseId,
            @ModelAttribute Lesson lesson,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) throws Exception {

        // Validate the lesson object
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the create lesson view
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lesson.create.error", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course/{courseId}/lesson/create";
        }

        // Get the course by ID
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isEmpty()) {
            // If the course is not found, redirect to the course list with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("course.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course";
        }

        // Save the lesson
        Lesson newLesson = lessonService.createLesson(lesson);

        // Check if the lesson was created successfully
        if (newLesson == null) {
            // If the lesson creation failed, redirect to the create lesson view with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lesson.create.error", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course/{courseId}/lesson/create";
        }

        // Redirect to the course page with a success message
        redirectAttributes.addFlashAttribute("msg_success", localizationService.getMessage("lesson.create.success", LocaleContextHolder.getLocale()));
        return "redirect:/admin/course/" + courseId;
    }

    @GetMapping("/course/{courseId}/lesson/{lessonId}")
    public  String lessonDetailView(
            Model model,
            @PathVariable("courseId") Integer courseId,
            @PathVariable("lessonId") Integer lessonId,
            RedirectAttributes redirectAttributes
    ) {

        // Get the course by ID
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isEmpty()) {
            // If the course is not found, redirect to the course list with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("course.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course";
        }

        // Get the lesson by ID
        Optional<Lesson> lesson = lessonService.getLessonById(lessonId);
        if (lesson.isEmpty()) {
            // If the lesson is not found, redirect to the course list with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lesson.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course/" + courseId + "/lessons";
        }

        // Get the lesson attachments
        List<LessonAttachment> currentlessonAttachments = lessonAttachmentService.getLessonAttachmentsByLesson(lessonId);

        // Get the lesson types
        List<LessonType> lessonTypes = lessonTypeService.getLessonTypes();

        //Mock login user
        User u = userService.getUserById(2);
        model.addAttribute("authUser", u);

        // Add the lesson and course to the model
        model.addAttribute("lesson", lesson.get());
        model.addAttribute("lessonTypes", lessonTypes);
        model.addAttribute("course", course.get());
        model.addAttribute("currentlessonAttachments", currentlessonAttachments);

        return "dashboard/admin/lesson/lesson_detail";
    }

    @PostMapping("/course/{courseId}/lesson/{lessonId}/update")
    public String updateLesson(
            Model model,
            @PathVariable("courseId") Integer courseId,
            @PathVariable("lessonId") Integer lessonId,
            @RequestParam(value = "notDeleteLessonAttachmentIds", required = false) List<Long> notDeleteLessonAttachmentIds,
            @ModelAttribute Lesson lesson,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) throws Exception {

        // Validate the lesson object
        if (bindingResult.hasErrors()) {
            // If there are validation errors, return to the create lesson view
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lesson.invalidData", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course/" + courseId + "/lesson/" + lessonId;
        }

        // Get the course by ID
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isEmpty()) {
            // If the course is not found, redirect to the course list with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("course.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/courses";
        }

        // Get the lesson by ID
        Optional<Lesson> existingLesson = lessonService.getLessonById(lessonId);
        if (existingLesson.isEmpty()) {
            // If the lesson is not found, redirect to the course list with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lesson.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course/" + courseId + "/lessons";
        }

//         Update the lesson
        Lesson updatedLesson = lessonService.updateLesson(lesson, notDeleteLessonAttachmentIds);

        // Check if the lesson was update successfully
        if (updatedLesson == null) {
            // If the lesson creation failed, redirect to the update lesson view with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lesson.update.error", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course/" + courseId + "/lesson/" + lessonId;
        }

        // Redirect to the course page with a success message
        redirectAttributes.addFlashAttribute("msg_success", localizationService.getMessage("lesson.update.success", LocaleContextHolder.getLocale()));
        return "redirect:/admin/course/" + courseId + "/lesson/" + lessonId;
    }

    @PostMapping("/course/{courseId}/lesson/{lessonId}/delete")
    public String deleteLesson(
            Model model,
            @PathVariable("courseId") Integer courseId,
            @PathVariable("lessonId") Integer lessonId,
            RedirectAttributes redirectAttributes
    ) throws Exception {

        // Get the course by ID
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isEmpty()) {
            // If the course is not found, redirect to the course list with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("course.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course";
        }

        // Get the lesson by ID
        Optional<Lesson> lesson = lessonService.getLessonById(lessonId);
        if (lesson.isEmpty()) {
            // If the lesson is not found, redirect to the course list with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lesson.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course/" + courseId + "/lessons";
        }

        // Delete the lesson
        boolean deleted = lessonService.deleteLesson(lessonId);

        // Check if the lesson was deleted successfully
        if (!deleted) {
            // If the lesson deletion failed, redirect to the update lesson view with an error message
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lesson.delete.error", LocaleContextHolder.getLocale()));
            return "redirect:/admin/course/" + courseId + "/lesson/" + lessonId;
        }

        // Redirect to the course page with a success message
        redirectAttributes.addFlashAttribute("msg_success", localizationService.getMessage("lesson.delete.success", LocaleContextHolder.getLocale()));
        return "redirect:/admin/course/" + courseId + "/lessons";
    }
}
