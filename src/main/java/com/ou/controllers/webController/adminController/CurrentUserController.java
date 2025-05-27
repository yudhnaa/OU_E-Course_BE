package com.ou.controllers.webController.adminController;

import com.ou.pojo.Lecturer;
import com.ou.pojo.User;
import com.ou.services.LocalizationService;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.Optional;

@Controller
public class CurrentUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private LocalizationService localizationService;


    @GetMapping("/profile")
    public String profile(
            Model model,
            Principal principal
    ) {
        Optional<User> currentUser = userService.getUserByUsername(principal.getName());

        if (currentUser.isEmpty()) {
            model.addAttribute("msg_error", localizationService.getMessage("user.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/";
        }

        model.addAttribute("user", currentUser.get());
        return "/dashboard/current-user/profile";
    }

    @PostMapping("/current-user/update")
    public String updateLecturer(
            @ModelAttribute("lecturer") User newUser,
            Principal principal,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.invalidData", LocaleContextHolder.getLocale()));
            return "redirect:/profile";
        }

        // Check if the lecturer exists
        User existingUser = userService.getUserById(newUser.getId());
        if (existingUser == null) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/profile";
        }

        //check login user and update only if the user is the same as the current user
        if (!principal.getName().equals(existingUser.getUsername())) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.update.error", LocaleContextHolder.getLocale()));
            return "redirect:/profile";
        }

        try {
            User updatedUser = userService.updateUser(newUser);
            redirectAttributes.addFlashAttribute("msg_success", localizationService.getMessage("user.update.success", LocaleContextHolder.getLocale()));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.update.error", LocaleContextHolder.getLocale()));
        }
        return "redirect:/profile";
    }

    @PostMapping("/current-user/updatePassword")
    public String updateLecturerPassword(
            @ModelAttribute("user") User newUser,
            RedirectAttributes redirectAttributes,
            BindingResult bindingResult,
            Principal principal
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.invalidData", LocaleContextHolder.getLocale()));
            return "redirect:/profile";
        }

        // Check if the lecturer exists
        User existingUser = userService.getUserById(newUser.getId());
        if (existingUser == null) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.notFound", LocaleContextHolder.getLocale()));
            return "redirect:/profile";
        }

        //check login user and update only if the user is the same as the current user
        if (!principal.getName().equals(existingUser.getUsername())) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.update.error", LocaleContextHolder.getLocale()));
            return "redirect:/profile";
        }

        // Update the password
        try {
            User updatedUser = userService.updateUser(newUser);

            if (updatedUser == null)
                redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.update.error", LocaleContextHolder.getLocale()));

            redirectAttributes.addFlashAttribute("msg_success", localizationService.getMessage("user.update.success", LocaleContextHolder.getLocale()));
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.update.error", LocaleContextHolder.getLocale()));
        }
        return "redirect:/profile";
    }
}
