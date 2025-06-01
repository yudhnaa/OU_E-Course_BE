package com.ou.controllers.webController.adminController;


import com.ou.exceptions.NotFoundException;
import com.ou.helpers.PaginationHelper;
import com.ou.pojo.*;
import com.ou.services.*;
import com.ou.utils.Pagination;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/courses/{courseId}/lessons/{lessonId}/exercises")
public class ExerciseController {
    @Autowired
    private ExerciseService exerciseService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private PaginationHelper paginationHelper;

    @Autowired
    private UserService userService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionTypeService questionTypeService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private LecturerService lecturerService;

    @Autowired
    private LocalizationService localizationService;


    @GetMapping
    public String getExercises(Model model,
                               @PathVariable("courseId") Integer courseId,
                               @PathVariable("lessonId") Integer lessonId,
                               @RequestParam Map<String, String> params,
                               @AuthenticationPrincipal CustomUserDetails principal) throws Exception {
        // Check if the user has permission to view the course
        Course course = courseService.getCourseByIdWithPermissionCheck(courseId, principal.getUser());
        params.put("courseId", String.valueOf(courseId));
        params.put("lessonId", String.valueOf(lessonId));
        params.put("page", params.getOrDefault("page", "1"));
        if(principal.getUser().getUserRoleId().getName().contains("LECTURER")){
            Lecturer lecturer = lecturerService.getLecturerByUserId(principal.getUser().getId())
                    .orElseThrow(() -> new NotFoundException(localizationService.getMessage("lecturer.notFound", LocaleContextHolder.getLocale())));

            params.put("createdByUserId", String.valueOf(lecturer.getId()));
        }

//        Optional<Course> course = courseService.getCourseById(courseId);

//        if (course.isEmpty()) {
//            model.addAttribute("msg_error", "Course not found.");
//            return "dashboard/lecturer/exercise/exercise";
//        }

        Optional<Lesson> lesson = lessonService.getLessonById(lessonId);
        if (lesson.isEmpty()) {
            model.addAttribute("msg_error", "Lesson not found.");
            return "dashboard/lecturer/exercise/exercise";
        }


        List<Exercise> exercises = exerciseService.getExercises(params);
        long totalExercises = exerciseService.countSearchResults(params);

        if(totalExercises == 0){
            model.addAttribute("msg_error", "No exercises found for this course.");
            return "dashboard/lecturer/exercise/exercise";
        }

        Pagination pagination = paginationHelper.getPagination(params, totalExercises);
        Map<Integer, String> lecturerNames = exercises.stream().collect(
                Collectors.toMap(
                        Exercise::getId,
                        ex -> {
                            if(ex.getCreatedByUserId() != null && ex.getCreatedByUserId().getUserId() != null){
                                return ex.getCreatedByUserId().getUserId().getUsername();
                            }else{
                                return "Unknown";
                            }
                        }
                )
        );
        model.addAttribute("exercises", exercises);
        model.addAttribute("lecturerNames", lecturerNames);
        model.addAttribute("courseId", courseId);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("lessonName", lesson.get().getName());
        model.addAttribute("courseName", course.getName());
        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());
        return "dashboard/lecturer/exercise/exercise"; // Return the name of the view to render
    }

    @GetMapping("/exercise/{exerciseId}")
    public String getExerciseDetails(Model model,
                                     @PathVariable("courseId") Integer courseId,
                                     @PathVariable("lessonId") Integer lessonId,
                                     @PathVariable("exerciseId") Integer exerciseId,
                                     @AuthenticationPrincipal CustomUserDetails principal) throws Exception {
        Exercise exercise = exerciseService.getExerciseByIdWithPermissionsCheck(exerciseId, principal.getUser());
        Optional<User> creator = Optional.ofNullable(userService.getUserById(exercise.getCreatedByUserId().getUserId().getId()));
        Optional<Course> course = courseService.getCourseById(courseId);
        if (course.isEmpty()) {
            model.addAttribute("msg_error", "Course not found.");
            return "dashboard/lecturer/exercise/exercise";
        }

        Optional<Lesson> lesson = lessonService.getLessonById(lessonId);
        if (lesson.isEmpty()) {
            model.addAttribute("msg_error", "Lesson not found.");
            return "dashboard/lecturer/exercise/exercise";
        }

        List<Question> questions = questionService.getQuestionsByExercise(exerciseId);
        List<QuestionType> questionTypes = questionTypeService.getAllQuestionTypes();

        model.addAttribute("exercise", exercise);
        model.addAttribute("creator", creator.orElse(null));
        model.addAttribute("courseId", courseId);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("course", course.get());
        model.addAttribute("lesson", lesson.get());
        model.addAttribute("questions", questions);
        model.addAttribute("questionTypes", questionTypes);
        return "dashboard/lecturer/exercise/exercise_detail"; // Return the name of the view to render
    }

    @GetMapping("/exercise/add")
    public String addExercise(Model model,
                                  @PathVariable("courseId") Integer courseId,
                                  @PathVariable("lessonId") Integer lessonId,
                                  @AuthenticationPrincipal CustomUserDetails principal) {
        // Check if the user has permission to create exercises for the course
        Course course = courseService.getCourseByIdWithPermissionCheck(courseId, principal.getUser());
        if (course == null) {
            model.addAttribute("msg_error", "Course not found.");
            return "dashboard/lecturer/exercise/exercise";
        }

        Optional<Lesson> lesson = lessonService.getLessonById(lessonId);
        if (lesson.isEmpty()) {
            model.addAttribute("msg_error", "Lesson not found.");
            return "dashboard/lecturer/exercise/exercise";
        }

        model.addAttribute("courseId", courseId);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("exercise", new Exercise());
        return "dashboard/lecturer/exercise/exercise_add"; // Return the name of the view to render
    }

    @PostMapping("/exercise/add")
    public String createExercise(@PathVariable("courseId") Integer courseId,
                                 @PathVariable("lessonId") Integer lessonId,
                                 @Valid @ModelAttribute("exercise") Exercise exercise,
                                 @AuthenticationPrincipal CustomUserDetails principal,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {
        // Check if the user has permission to create exercises for the course
        Course course = courseService.getCourseByIdWithPermissionCheck(courseId, principal.getUser());
//        Optional<Course> course = courseService.getCourseById(courseId);
//        if (course.isEmpty()) {
//            model.addAttribute("msg_error", "Course not found.");
//            return "dashboard/lecturer/exercise/exercise";
//        }

        // Check if the user has permission to create exercises for the lesson
        if (principal.getUser().getUserRoleId().getName().contains("LECTURER")) {
            Lecturer lecturer = lecturerService.getLecturerByUserId(principal.getUser().getId())
                    .orElseThrow(() -> new NotFoundException(localizationService.getMessage("lecturer.notFound", LocaleContextHolder.getLocale())));

            exercise.setCreatedByUserId(lecturer);
        }

        Lesson lesson = lessonService.getLessonById(lessonId)
                .orElseThrow(() -> new NotFoundException("Lesson not found"));
        exercise.setLessonId(lesson);
        exercise.setCourseId(course);
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exercise", result);
            redirectAttributes.addFlashAttribute("exercise", exercise);
            return "redirect:/admin/courses/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/add";
        }
        try {
            exerciseService.createExercise(exercise);
            redirectAttributes.addFlashAttribute("msg_success", "Exercise created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", "Error creating exercise: " + e.getMessage());
        }
        return "redirect:/admin/courses/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exercise.getId();
    }

    @PostMapping("/exercise/{exerciseId}/update")
    public String updateExercise(@PathVariable("courseId") Integer courseId,
                                 @PathVariable("lessonId") Integer lessonId,
                                 @PathVariable("exerciseId") Integer exerciseId,
                                 @Valid @ModelAttribute("exercise") Exercise exerciseUpdate,
                                 BindingResult result,
                                 @AuthenticationPrincipal CustomUserDetails principal,
                                 RedirectAttributes redirectAttributes,
                                 Model model) throws Exception {
        Exercise existingExercise = exerciseService.getExerciseByIdWithPermissionsCheck(exerciseId, principal.getUser());
        existingExercise.setName(exerciseUpdate.getName());
        existingExercise.setDurationMinutes(exerciseUpdate.getDurationMinutes());
        existingExercise.setMaxScore(exerciseUpdate.getMaxScore());

        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.exercise", result);
            redirectAttributes.addFlashAttribute("exercise", exerciseUpdate);
            return "redirect:/admin/courses/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId + "#exercise-detail";
        }

        try {
            exerciseService.updateExercise(existingExercise); // Lưu đối tượng đã tồn tại
            redirectAttributes.addFlashAttribute("msg_success", "Updated successfully!!!!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", "Error: " + e.getMessage());
        }
        return "redirect:/admin/courses/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId;
    }

    @PostMapping("/exercise/{exerciseId}/delete")
    public String deleteExercise(@PathVariable("courseId") Integer courseId,
                                 @PathVariable("lessonId") Integer lessonId,
                                 @PathVariable("exerciseId") Integer exerciseId,
                                 @AuthenticationPrincipal CustomUserDetails principal,
                                 RedirectAttributes redirectAttributes) {
        // Check if the user is lecturer and has permission to delete the exercise
        if (principal.getUser().getUserRoleId().getName().contains("LECTURER")) {
            Exercise exercise = exerciseService.getExerciseByIdWithPermissionsCheck(exerciseId, principal.getUser());
            if (exercise == null) {
                redirectAttributes.addFlashAttribute("msg_error", "Exercise not found or you do not have permission to delete it.");
                return "redirect:/admin/courses/" + courseId + "/lessons/" + lessonId + "/exercises";
            }
        }
        try {
            if (!exerciseService.deleteExercise(exerciseId)) {
                redirectAttributes.addFlashAttribute("msg_error", "Exercise not found or already deleted.");
            } else {
                redirectAttributes.addFlashAttribute("msg_success", "Deleted successfully!!!!");
            }
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", "Error: " + e.getMessage());
        }
        return "redirect:/admin/courses/" + courseId + "/lessons/" + lessonId + "/exercises";
    }

}
