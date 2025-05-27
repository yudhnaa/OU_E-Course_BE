package com.ou.controllers.webController;

import com.ou.pojo.User;
import com.ou.pojo.UserRole;
import com.ou.services.LocalizationService;
import com.ou.services.UserRoleService;
import com.ou.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
public class AuthenticationController {

    @Autowired
    UserService userService;
    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private UserRoleService userRoleService;

    @RequestMapping("/login")
    public String login()
    {
        return "/authentication/login";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        User user = new User();

        List<UserRole> userRoles = userRoleService.getAllUserRoles();

        model.addAttribute("userRoles", userRoles);
        model.addAttribute("user", user);

        return "/authentication/register";
    }

    @PostMapping(value = "/register")
    public String register(
            @Valid @ModelAttribute ("user") User user,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes

    ) throws IOException {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("msg_error", localizationService.getMessage("user.create.error.validation", LocaleContextHolder.getLocale()));
            return "redirect:/register";
        }

        return "redirect:/login";

    }
}
