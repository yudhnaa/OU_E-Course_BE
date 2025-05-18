package com.ou.controllers.webController;

import com.ou.exceptions.NotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice(basePackages = "com.ou.controllers.webController")
public class WebExceptionHandler {

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
}
