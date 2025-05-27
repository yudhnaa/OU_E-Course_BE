package com.ou.controllers.webController;

import com.ou.exceptions.NotFoundException;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages = "com.ou.controllers.webController")
public class WebExceptionHandler {

    @Autowired
    private LocalizationService localizationService;

    @ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, Model model) {
        ModelAndView mav = new ModelAndView("error");
        mav.addObject("msg_error", ex.getMessage());
        return mav;
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handleNotFoundException(NotFoundException ex, Model model) {
        ModelAndView mav = new ModelAndView("not-found");
        mav.addObject("msg_error", ex.getMessage());
        return mav;
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(Model model) {
        model.addAttribute("msg_error", localizationService.getMessage("permission.access.denied", LocaleContextHolder.getLocale()));
        return "others/access-denied";
    }
}
