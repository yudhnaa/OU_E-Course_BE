package com.ou.controllers.webController.adminController;

import com.ou.exceptions.NotFoundException;
import com.ou.helpers.PaginationHelper;
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

import java.security.Principal;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PaginationHelper paginationHelper;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private AdminService adminService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private LecturerService lecturerService;

    @GetMapping("/users")
    public String users(
            Model model,
            @RequestParam Map<String, String> params,
            RedirectAttributes redirectAttributes
            ) {

        long totalItems = userService.countSearchResults(params);
        Pagination pagination = paginationHelper.getPagination(params, totalItems);

        List<User> users = userService.searchUsers(params, params);
        User newUser = new User();

        List<UserRole> userRoles = userRoleService.getAllUserRoles();

        model.addAttribute("users", users);
        model.addAttribute("newUser", newUser);
        model.addAttribute("userRoles", userRoles);

        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages() > 0 ? pagination.getTotalPages() : 1);
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("filterName", params.getOrDefault("name", " "));

        return "/dashboard/admin/user/user_list";
    }

    @PostMapping("/user/add")
    public String addUser(
            @ModelAttribute User newUser,
            @RequestParam Map<String, String> params,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg_error", "Error while adding user. Please check the input data.");
            return "redirect:/admin/users";
        }

        try {
            User createdUser = userService.addUser(newUser);
            UserRole userRole = userRoleService.getUserRoleById(newUser.getUserRoleId().getId());

            switch (userRole.getName())
            {
                case "ROLE_ADMIN":
                    Admin newAdmin = new Admin();
                    newAdmin.setUserId(createdUser);
                    Admin createdAdmin = adminService.addAdmin(newAdmin);
                    break;
                case "ROLE_LECTURER":
                    Lecturer newLecturer = new Lecturer();
                    newLecturer.setUserId(createdUser);
                    newLecturer.setIsActive(true);
                    Lecturer createdLecturer = lecturerService.addLecturer(newLecturer);
                    break;
                case "ROLE_STUDENT":
                    Student newStudent = new Student();
                    newStudent.setUserId(createdUser);
                    Student createdStudent = studentService.addStudent(newStudent);
                    break;
                default:
                    redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.create.error.role", LocaleContextHolder.getLocale()));
                    return "redirect:/admin/users";
            }

            redirectAttributes.addFlashAttribute("msg_success", localizationService.getMessage("user.create.success", LocaleContextHolder.getLocale()));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.create.error", LocaleContextHolder.getLocale()));
        }

        return "redirect:/admin/users";
    }

    @PostMapping("/user/{id}/delete")
    public String deleteUser(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        Locale locale = LocaleContextHolder.getLocale();

        try {
            User deleteUser = userService.getUserById(id);
            if (deleteUser == null) {
                redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.not.found", locale));
                return "redirect:/admin/users";
            }

            String roleName = deleteUser.getUserRoleId().getName();

            boolean roleDeleted = switch (roleName) {
                case "ROLE_ADMIN" -> deleteAdmin(deleteUser);
                case "ROLE_LECTURER" -> deleteLecturer(deleteUser);
                case "ROLE_STUDENT" -> deleteStudent(deleteUser);
                default -> {
                    redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.delete.error.notFound", locale));
                    yield false;
                }
            };

            if (!roleDeleted) {
                redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.delete.error", locale));
                return "redirect:/admin/users";
            }

            boolean isDeleted = userService.deleteUser(id);
            if (isDeleted) {
                redirectAttributes.addFlashAttribute("msg_success", localizationService.getMessage("user.delete.success", locale));
            } else {
                redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.delete.error", locale));
            }
        }
        catch (NotFoundException e){
            // try to delete user even if the role is not found
            boolean isDeleted = userService.deleteUser(id);
            if (isDeleted) {
                redirectAttributes.addFlashAttribute("msg_success", localizationService.getMessage("user.delete.success", locale));
            } else {
                redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.delete.error", locale));
            }
            return "redirect:/admin/users";
        }
        catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.delete.error", LocaleContextHolder.getLocale()));
        }

        return "redirect:/admin/users";
    }

    // Helper methods
    private boolean deleteAdmin(User user) {

        Admin adminToDel  = adminService.getAdminByUserId(user.getId());
        if (adminToDel == null) {
            return false;
        }
        return adminService.deleteAdmin(adminToDel.getId());
    }

    private boolean deleteLecturer(User user) {
        Optional<Lecturer> lecturerToDel = lecturerService.getLecturerByUserId(user.getId());

        if (lecturerToDel.isEmpty()) {
            return false;
        }
        return lecturerService.deleteLecturer(lecturerToDel.get().getId());
    }

    private boolean deleteStudent(User user) {
        Student studentToDel = studentService.getStudentByUserId(user.getId());

        if (studentToDel == null) {
            return false;
        }
        return studentService.deleteStudent(studentToDel.getId());
    }
}
