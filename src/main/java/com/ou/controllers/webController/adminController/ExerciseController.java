package com.ou.controllers.webController.adminController;


import com.ou.helpers.PaginationHelper;
import com.ou.pojo.Exercise;
import com.ou.services.CourseService;
import com.ou.services.ExerciseService;
import com.ou.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course/{courseId}/exercises")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private PaginationHelper paginationHelper;

    @GetMapping
    public String getExercises(Model model,
                               @PathVariable Integer courseId,
                               @RequestParam Map<String, String> params) {
        long totalExercises = exerciseService.countExercisesByCourse(courseId);

        if(totalExercises == 0){
            model.addAttribute("message", "No exercises found for this course.");
            return "dashboard/lecturer/exercise/exercise";
        }

        Pagination pagination = paginationHelper.getPagination(params, totalExercises);

        List<Exercise> exercises = exerciseService.getExercisesByCourse(courseId,params);
        model.addAttribute("exercises", exercises);
        model.addAttribute("courseId", courseId);

        return "dashboard/lecturer/exercise/exercise"; // Return the name of the view to render
    }
}
