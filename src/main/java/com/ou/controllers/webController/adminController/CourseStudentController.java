package com.ou.controllers.webController.adminController;

import com.ou.helpers.PaginationHelper;
import com.ou.pojo.CourseStudent;
import com.ou.services.CourseService;
import com.ou.services.CourseStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping(path = "/admin")
public class CourseStudentController {

    @Autowired
    private CourseStudentService courseStudentService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private PaginationHelper pagination;

    @PostMapping("/course/{courseId}/student/{studentId}/delete")
    public String deleteCourseStudent(@PathVariable("courseId") int courseId,
                                      @PathVariable("studentId") int studentId,
                                      RedirectAttributes redirectAttributes
    ) throws Exception {

        boolean result = courseStudentService.deleteCourseStudent(studentId);

        if (!result) {
            String msg_error = "Failed to delete student from course";
            redirectAttributes.addFlashAttribute("msg_error", msg_error);
        }
        else {
            String msg_success = "Student deleted from course successfully";
            redirectAttributes.addFlashAttribute("msg_success", msg_success);
        }

        return "redirect:/admin/course/" + courseId + "#course-team";
    }

}
