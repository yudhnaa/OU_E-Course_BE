package com.ou.controllers.webController.adminController;

import com.ou.controllers.webController.UserController;
import com.ou.dto.StudentDto;
import com.ou.exceptions.NotFoundException;
import com.ou.helpers.PaginationHelper;
import com.ou.mappers.StudentMapper;
import com.ou.pojo.Lecturer;
import com.ou.pojo.Student;
import com.ou.pojo.User;
import com.ou.services.LocalizationService;
import com.ou.services.StudentService;
import com.ou.services.UserService;
import com.ou.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/admin")
public class StudentController {

    @Autowired
    private UserController userController;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private PaginationHelper paginationHelper;
    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private UserService userService;

    @GetMapping("/students")
    public String index(
            Model model,
            @RequestParam Map<String,String> params,
            RedirectAttributes redirectAttributes
    ) {
        List<Student> students = studentService.searchStudents(params, params);
        List<StudentDto> studentDtos = new ArrayList<>();
        if (students != null) {
            studentDtos = students.stream().map(student -> studentMapper.toDto(student)).toList();
        }

        long totalItems;
        if (params.get("name") != null) {
            totalItems = studentService.countSearchResults(params);
        } else {
            totalItems = studentService.countStudents();
        }

        Pagination pagination = paginationHelper.getPagination(params, totalItems);
        params.put("page", String.valueOf(pagination.getCurrentPage()));


        model.addAttribute("students", studentDtos);

        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages() > 0 ? pagination.getTotalPages() : 1);
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("filterName", params.getOrDefault("name", ""));

        return "dashboard/admin/student/student_list";
    }

    @GetMapping("/student/{id}")
    public String courseDetail(Model model,
                               @PathVariable("id") int id,
                               @RequestParam Map<String, String> params,
                               RedirectAttributes redirectAttributes

    ) throws Exception {

        Student student = studentService.getStudentById(id);
        if (student == null) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("student.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/students";

        }

        model.addAttribute("student", student);
        model.addAttribute("user", student.getUserId());

        return "dashboard/admin/student/student_detail";
    }

    @PostMapping("/student/{id}/update")
    public String updateStudent(
            @PathVariable("id") int id,
            @ModelAttribute("student") Student student,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("student.invalidData", LocaleContextHolder.getLocale()));
            return "redirect:/admin/student/" + id;
        }

        // Check if the student exists
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent == null) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("student.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/students";
        }

        // Update the student
        try {
            User updatedUser = userService.updateUser(student.getUserId());
            Student updatedStudent = studentService.updateStudent(student);
            if (updatedUser == null || updatedStudent == null) {
                redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("student.update.error", LocaleContextHolder.getLocale()));
                return "redirect:/admin/student/" + id;
            }
            redirectAttributes.addFlashAttribute("msg_success", localizationService.getMessage("student.update.success", LocaleContextHolder.getLocale()));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("student.update.error", LocaleContextHolder.getLocale()));
        }
        return "redirect:/admin/student/" + id;
    }

    @PostMapping("/student/{id}/updatePassword")
    public String updateStudentPassword(
            @PathVariable("id") int id,
            @ModelAttribute("user") User user,
            RedirectAttributes redirectAttributes
    ) {
        // Check if the student exists
        Student existingStudent = studentService.getStudentById(id);
        if (existingStudent == null) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("student.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/students";
        }

        // Update the password
        try {
            User updatedUser = userService.updateUser(user);

            if (updatedUser == null)
                redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("student.update.error", LocaleContextHolder.getLocale()));
            else
                redirectAttributes.addFlashAttribute("msg_success", localizationService.getMessage("student.update.success", LocaleContextHolder.getLocale()));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("student.update.error", LocaleContextHolder.getLocale()));
        }
        return "redirect:/admin/student/" + id;
    }
}
