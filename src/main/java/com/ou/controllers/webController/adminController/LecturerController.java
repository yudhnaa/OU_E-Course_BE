package com.ou.controllers.webController.adminController;

import com.ou.dto.LecturerDto;
import com.ou.exceptions.NotFoundException;
import com.ou.formatters.UserFormatter;
import com.ou.helpers.PaginationHelper;
import com.ou.mappers.LecturerMapper;
import com.ou.pojo.*;
import com.ou.services.LecturerService;
import com.ou.services.LocalizationService;
import com.ou.services.UserService;
import com.ou.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
public class LecturerController {

    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private PaginationHelper paginationHelper;
    @Autowired
    private LecturerMapper lecturerMapper;
    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserFormatter userFormatter;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/lecturers")
    public String lecturers(
            Model model,
            @RequestParam Map<String,String> params,
            RedirectAttributes redirectAttributes
    ) {
        // Get the total number of lecturers

        long totalItems;
        if (params.get("name") != null) {
            totalItems = lecturerService.countSearchResults(params);
        } else {
            totalItems = lecturerService.countLecturers();
        }

        Pagination pagination = paginationHelper.getPagination(params, totalItems);

        List<LecturerDto> lecturers = lecturerService.getLecturers(params).stream().map(l ->
                lecturerMapper.toDto(l)).collect(Collectors.toList()
        );

        if (lecturers.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lecturer.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/lecturers";
        }

        model.addAttribute("lecturers", lecturers);

        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages() > 0 ? pagination.getTotalPages() : 1);
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("filterName", params.getOrDefault("name", ""));


        return "/dashboard/admin/lecturer/lecturer_list";
    }

    @GetMapping("/lecturer/{id}")
    public String courseDetail(Model model,
                               @PathVariable("id") int id,
                               @RequestParam Map<String, String> params
    ) throws Exception {

        Optional<Lecturer> lecturer = lecturerService.getLecturerById(id);

        if (lecturer.isEmpty()) {
            lecturer = lecturerService.getLecturerByUserId(id);

            if (lecturer.isEmpty()) {
                throw new NotFoundException(localizationService.getMessage("lecturer.notFound", LocaleContextHolder.getLocale()));
            }
        }



        model.addAttribute("lecturer", lecturer.get());
        model.addAttribute("user", lecturer.get().getUserId());

        return "dashboard/admin/lecturer/lecturer_detail";
    }

    @PostMapping("/lecturer/{id}/update")
    public String updateLecturer(
            @PathVariable("id") int id,
            @ModelAttribute("lecturer") Lecturer lecturer,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lecturer.invalidData", LocaleContextHolder.getLocale()));
            return "redirect:/admin/lecturer/" + id;
        }

        // Check if the lecturer exists
        Optional<Lecturer> existingLecturer = lecturerService.getLecturerById(id);
        if (existingLecturer.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lecturer.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/lecturers";
        }

        // Update the lecturer
        User updatedUser = userService.updateUser(lecturer.getUserId());
        Lecturer updatedLecturer = lecturerService.updateLecturer(lecturer);
        if (updatedUser == null || updatedLecturer == null) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lecturer.update.error", LocaleContextHolder.getLocale()));
            return "redirect:/admin/lecturer/" + id;
        }

        try {
            lecturerService.updateLecturer(lecturer);
            redirectAttributes.addFlashAttribute("msg_success", localizationService.getMessage("lecturer.update.success", LocaleContextHolder.getLocale()));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lecturer.update.error", LocaleContextHolder.getLocale()));
        }
        return "redirect:/admin/lecturer/" + id;
    }

    @PostMapping("/lecturer/{id}/updatePassword")
    public String updateLecturerPassword(
            @PathVariable("id") int id,
            @ModelAttribute("user") User user,
            RedirectAttributes redirectAttributes
    ) {
        // Check if the lecturer exists
        Optional<Lecturer> existingLecturer = lecturerService.getLecturerById(id);
        if (existingLecturer.isEmpty()) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lecturer.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/admin/lecturers";
        }

        // Update the password
        try {
            User updatedUser = userService.updateUser(user);

            if (updatedUser == null)
                redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lecturer.update.error", LocaleContextHolder.getLocale()));

            redirectAttributes.addFlashAttribute("msg_success", localizationService.getMessage("lecturer.update.success", LocaleContextHolder.getLocale()));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("lecturer.update.error", LocaleContextHolder.getLocale()));
        }
        return "redirect:/admin/lecturer/" + id;
    }
}