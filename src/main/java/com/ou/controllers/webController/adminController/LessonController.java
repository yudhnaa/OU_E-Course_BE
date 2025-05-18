package com.ou.controllers.webController.adminController;


import com.ou.pojo.Course;
import com.ou.pojo.Lesson;
import com.ou.pojo.LessonType;
import com.ou.services.CourseService;
import com.ou.services.LessonService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LessonController {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private CourseService courseService;

    @Autowired
    LocalizationService localizationService;

//    @Autowired
//    LessonTypeService lessonTypeService;

    @GetMapping("/admin/course/{courseId}/lesson/create")
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



        // Add the lesson and course to the model
        model.addAttribute("lesson", lesson);
        model.addAttribute("course", course.get());

        return "dashboard/admin/course/course_lesson_create";
    }

    @PostMapping("/admin/course/{courseId}/lesson/create")
    public String createLesson(
            Model model,
            @PathVariable("courseId") Integer courseId,
            RedirectAttributes redirectAttributes
    ){


        return "redirect:/admin/course/{courseId}/lesson/create";
    }

}
