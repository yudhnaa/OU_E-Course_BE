package com.ou.controllers.webController.adminController;


import com.ou.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course/{courseId}/exercises")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;





}
