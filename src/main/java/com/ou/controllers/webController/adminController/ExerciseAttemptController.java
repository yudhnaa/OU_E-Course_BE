package com.ou.controllers.webController.adminController;

import com.ou.helpers.PaginationHelper;
import com.ou.pojo.*;
import com.ou.services.*;
import com.ou.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/course/{courseId}/lessons/{lessonId}/exercises/exercise/{exerciseId}/submissions")
public class ExerciseAttemptController {
    @Autowired
    private ExerciseAttemptService exerciseAttemptService;

    @Autowired
    private PaginationHelper paginationHelper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private MultipleChoiceAnswerService multipleChoiceAnswerService;

    @Autowired
    private WritingAnswerService writingAnswerService;

    @Autowired
    private ExerciseAttemptAnswerService exerciseAttemptAnswerService;



    @GetMapping
    public String getExerciseSubmissions(Model model,
                                         @PathVariable(value = "courseId") Integer courseId,
                                         @PathVariable(value = "lessonId") Integer lessonId,
                                         @PathVariable(value = "exerciseId") Integer exerciseId,
                                         @RequestParam Map<String,String> params) {
        // Fetch exercise attempts for the given exerciseId
        List<ExerciseAttempt> exerciseAttempts = exerciseAttemptService.getExerciseAttemptsByExerciseId(exerciseId, params);
        if (exerciseAttempts.isEmpty()) {
            model.addAttribute("message", "No submissions found for this exercise.");
        } else {
            model.addAttribute("exerciseAttempts", exerciseAttempts);
        }

        // Prepare pagination
        long totalSubmissions = exerciseAttemptService.countExerciseAttemptsByExerciseId(exerciseId);
        Pagination pagination = paginationHelper.getPagination(params,totalSubmissions);

        Map<Integer,String> studentNames = exerciseAttempts.stream().collect(
                Collectors.toMap(
                        ExerciseAttempt::getId,
                        ex -> {
                            if(ex.getStudentId() != null && ex.getStudentId().getUserId() != null){
                                return ex.getStudentId().getUserId().getUsername();
                            }
                            else{
                                return "Unknown Student";
                            }
                        }
                )
        );

        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());
        model.addAttribute("studentNames", studentNames);
        model.addAttribute("courseId", courseId);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("exerciseId", exerciseId);
        model.addAttribute("exerciseAttempts", exerciseAttempts);
        return "dashboard/lecturer/submission/exercise_submission_list";
    }

    @GetMapping("/{submissionId}")
    public String getExerciseSubmissionDetail(Model model,
                                              @PathVariable(value = "courseId") Integer courseId,
                                              @PathVariable(value = "lessonId") Integer lessonId,
                                              @PathVariable(value = "exerciseId") Integer exerciseId,
                                              @PathVariable(value = "submissionId") Integer submissionId) {
        // Fetch the specific exercise attempt by submissionId
        ExerciseAttempt exerciseAttempt = exerciseAttemptService.getExerciseAttemptById(submissionId)
                .orElseThrow(() -> new IllegalArgumentException("Submission not found"));

        // Get all the quesion in the exercise
        List<Question> questions = questionService.getQuestionsByExercise(exerciseId);

        // get exercise attempt's answers
        List<ExerciseAttemptAnswer> exerciseAttemptAnswers = exerciseAttemptAnswerService.getExerciseAttemptAnswersByAttemptId(submissionId,null);

        Map<Question, Map<String, Object>> questionDetails = new HashMap<>();
        for(Question question : questions){
            Map<String, Object> details = new HashMap<>();
            Optional<ExerciseAttemptAnswer> studentAnswerOpt = exerciseAttemptAnswers.stream()
                    .filter(answer -> answer.getQuestionId().getId().equals(question.getId()))
                    .findFirst();
            studentAnswerOpt.ifPresent(answer -> details.put("studentAnswer", answer.getAnswerText()));
            String questionType = question.getQuestionTypeId().getName();
            details.put("questionType", questionType);
            if("multiple choice".equalsIgnoreCase(questionType)) {
                List<MultipleChoiceAnswer> answers = multipleChoiceAnswerService.getAnswersByQuestionId(question.getId());
                details.put("answers", answers);
            }
            else if("writing".equalsIgnoreCase(questionType)) {
                WritingAnswer modelAnswer = writingAnswerService.getWritingAnswerByQuestionId(question.getId());
                details.put("modelAnswers", modelAnswer);
            }
            questionDetails.put(question,details);
        }

        model.addAttribute("exerciseAttempt", exerciseAttempt);
        model.addAttribute("courseId", courseId);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("exerciseId", exerciseId);
        model.addAttribute("questionDetails", questionDetails);
        return "dashboard/lecturer/submission/exercise_submission_detail";
    }

    @PostMapping("/{submissionId}/update")
    public String updateExerciseSubmission(@ModelAttribute("exerciseAttempt") ExerciseAttempt exerciseAttempt,
                                           @PathVariable(value = "courseId") Integer courseId,
                                           @PathVariable(value = "lessonId") Integer lessonId,
                                           @PathVariable(value = "exerciseId") Integer exerciseId,
                                           RedirectAttributes redirectAttributes) {
        // Validate and update the exercise attempt
        if (exerciseAttempt.getId() == null || exerciseAttempt.getId() <= 0) {
            throw new IllegalArgumentException("Invalid submission ID");
        }
        ExerciseAttempt updatedAttempt = exerciseAttemptService.updateExerciseAttempt(exerciseAttempt);

        if (updatedAttempt != null){
            redirectAttributes.addFlashAttribute("msg_success", "Submission updated successfully.");
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Failed to update submission.");
        }

        // Redirect to the submission detail page after update
        return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId + "/submissions/" + updatedAttempt.getId();
    }


}
