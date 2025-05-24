package com.ou.controllers.webController.adminController;

import com.ou.pojo.Question;
import com.ou.pojo.QuestionType;
import com.ou.pojo.Exercise;
import com.ou.services.MultipleChoiceAnswerService;
import com.ou.services.QuestionService;
import com.ou.services.QuestionTypeService;
import com.ou.services.WritingAnswerService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.util.List;

@Controller
@RequestMapping("/course/{courseId}/lessons/{lessonId}/exercises/exercise/{exerciseId}/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionTypeService questionTypeService;

    @Autowired
    private MultipleChoiceAnswerService multipleChoiceAnswerService;

    @Autowired
    private WritingAnswerService writingAnswerService;


    @GetMapping("/{questionId}")
    public String getQuestionById(@PathVariable("courseId") Integer courseId,
                                  @PathVariable("lessonId") Integer lessonId,
                                  @PathVariable("exerciseId") Integer exerciseId,
                                  @PathVariable("questionId") Integer questionId,
                                  Model model) {

        Question question = questionService.getQuestionById(questionId);
        if (question == null) {
            model.addAttribute("msg_error", "Question not found.");
            return "dashboard/lecturer/question/question_detail";
        }

        model.addAttribute("question", question);

        if (question.getQuestionTypeId().getName().equals("multiple choice")) {
            model.addAttribute("answers", multipleChoiceAnswerService.getAnswersByQuestionId(questionId));
        } else if (question.getQuestionTypeId().getName().equals("writing")) {
            model.addAttribute("answers", writingAnswerService.getWritingAnswerByQuestionId(questionId));
        }

        model.addAttribute("courseId", courseId);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("exerciseId", exerciseId);
        model.addAttribute("questionId", questionId);
        return "dashboard/lecturer/question/question_detail";
    }

    @PostMapping("/{questionId}")
    public String updateQuestion(@PathVariable("courseId") Integer courseId,
                                 @PathVariable("lessonId") Integer lessonId,
                                 @PathVariable("exerciseId") Integer exerciseId,
                                 @PathVariable("questionId") Integer questionId,
                                 @RequestParam("content") String content,
                                 @RequestParam(value = "options", required = false) List<String> options,
                                 @RequestParam(value = "answerIds", required = false) List<Integer> answerIds,
                                 @RequestParam(value = "deletedAnswerIds",required = false) String deletedAnswerIds,
                                 @RequestParam(value = "correctOption", required = false) Integer correctOption,
                                 @RequestParam(value = "sampleAnswer", required = false) String sampleAnswer,
                                 @RequestParam(value = "writingAnswerId", required = false) Integer writingAnswerId,
                                 RedirectAttributes redirectAttributes) {

        try {
            Question question = questionService.getQuestionById(questionId);
            if (question == null) {
                redirectAttributes.addFlashAttribute("msg_error", "Question not found.");
                return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId;
            }

            // Update question properties
            question.setContent(content);

            if(deletedAnswerIds != null && !deletedAnswerIds.isEmpty()){
                String[] ids = deletedAnswerIds.split(",");
                for(String id : ids){
                    Integer answerId = Integer.parseInt(id);
                    multipleChoiceAnswerService.deleteMultipleChoiceAnswer(answerId);
                }
            }

            // Update the question
            questionService.updateQuestion(question);

            // Update answers based on question type
            String questionType = question.getQuestionTypeId().getName();
            if (questionType.equals("multiple choice") && options != null && answerIds != null) {
                if (options.size() == answerIds.size()) {
                    multipleChoiceAnswerService.updateMultipleChoiceAnswer(questionId, answerIds, options, correctOption);
                } else {
                    redirectAttributes.addFlashAttribute("msg_error", "Options and answer IDs don't match.");
                    return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId + "/question/" + questionId;
                }
            } else if (questionType.equals("writing") && sampleAnswer != null) {
                writingAnswerService.updateWritingAnswer(questionId,writingAnswerId,sampleAnswer);
            }

            redirectAttributes.addFlashAttribute("msg_success", "Question updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", "Error updating question: " + e.getMessage());
        }

        return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId + "/question/" + questionId;
    }

    @GetMapping("/add")
    public String showAddQuestionForm(@PathVariable("courseId") Integer courseId,
                                      @PathVariable("lessonId") Integer lessonId,
                                      @PathVariable("exerciseId") Integer exerciseId,
                                      Model model) {
        List<QuestionType> questionTypes = questionTypeService.getAllQuestionTypes();
        Question question = new Question();
        // Pre-populate the exercise
        question.setExerciseId(new Exercise(exerciseId));

        model.addAttribute("questionTypes", questionTypes);
        model.addAttribute("courseId", courseId);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("exerciseId", exerciseId);
        model.addAttribute("question", question);
        return "dashboard/lecturer/question/question_add";
    }

    @PostMapping("/add")
    public String addQuestion(@PathVariable("courseId") Integer courseId,
                              @PathVariable("lessonId") Integer lessonId,
                              @PathVariable("exerciseId") Integer exerciseId,
                              @ModelAttribute("question") Question question,
                              @RequestParam("questionTypeId") Integer questionTypeId,
                              @RequestParam(value = "sampleAnswer", required = false) String sampleAnswer,
                              @RequestParam(value = "options", required = false) List<String> options,
                              @RequestParam(value = "correctOption", required = false) Integer correctOption,
                              BindingResult result,
                              RedirectAttributes redirectAttributes) {

        try {
            // Set the complex objects manually after basic binding
            question.setExerciseId(new Exercise(exerciseId));
            QuestionType type = questionTypeService.getQuestionTypeById(questionTypeId);
            question.setQuestionTypeId(type);

            // Basic validation
            if (question.getContent() == null || question.getContent().trim().isEmpty()) {
                redirectAttributes.addFlashAttribute("msg_error", "Question content is required.");
                return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId + "/question/add";
            }

            String questionTypeName = type.getName();

            if (questionTypeName.equals("multiple choice")) {
                if (options != null && !options.isEmpty()) {
                    questionService.addQuestion(question);
                    if (multipleChoiceAnswerService.addMultipleChoiceAnswer(question, options, correctOption)) {
                        redirectAttributes.addFlashAttribute("msg_success", "Question added successfully!");
                        return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId;
                    } else {
                        redirectAttributes.addFlashAttribute("msg_error", "Failed to add question.");
                        return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId + "/question/add";
                    }
                } else {
                    redirectAttributes.addFlashAttribute("msg_error", "Please provide answer options.");
                    return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId + "/question/add";
                }
            } else if (questionTypeName.equals("writing")) {
                if (sampleAnswer != null && !sampleAnswer.isEmpty()) {
                    questionService.addQuestion(question);
                    if (writingAnswerService.addWritingAnswer(question, sampleAnswer)) {
                        redirectAttributes.addFlashAttribute("msg_success", "Question added successfully!");
                        return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId;
                    } else {
                        redirectAttributes.addFlashAttribute("msg_error", "Failed to add writing answer.");
                        return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId + "/question/add";
                    }
                } else {
                    redirectAttributes.addFlashAttribute("msg_error", "Please provide a sample answer.");
                    return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId + "/question/add";
                }
            } else {
                redirectAttributes.addFlashAttribute("msg_error", "Unsupported question type.");
                return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId + "/question/add";
            }

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", "Error adding question: " + e.getMessage());
            return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId + "/question/add";
        }
    }

    @GetMapping("/{questionId}/delete")
    public String deleteQuestion(
            @PathVariable("courseId") Integer courseId,
            @PathVariable("lessonId") Integer lessonId,
            @PathVariable("exerciseId") Integer exerciseId,
            @PathVariable("questionId") Integer questionId,
            RedirectAttributes redirectAttributes) {
        try {
            questionService.deleteQuestion(questionId);
            redirectAttributes.addFlashAttribute("msg_success", "Question deleted successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("msg_error", "Error deleting question: " + e.getMessage());
        }

        return "redirect:/course/" + courseId + "/lessons/" + lessonId + "/exercises/exercise/" + exerciseId;
    }

}