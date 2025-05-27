package com.ou.controllers.webController;

import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorController {
    @Autowired
    private LocalizationService localizationService;

    //    Xu ly them trong Spring Security de chuyen huong ve cotroller nay
    @GetMapping("/access-denied")
    public String handleAccessDeniedException(Model model) {
        model.addAttribute("msg_error", localizationService.getMessage("permission.access.denied", LocaleContextHolder.getLocale()));
        return "others/access-denied";
    }
}
