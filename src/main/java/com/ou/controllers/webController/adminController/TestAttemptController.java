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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/course/{courseId}/tests/test/{testId}/submissions")
public class TestAttemptController {

    @Autowired
    private TestAttemptService testAttemptService;

    @Autowired
    private PaginationHelper paginationHelper;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private TestAttemptAnswerService testAttemptAnswerService;

    @Autowired
    private MultipleChoiceAnswerService multipleChoiceAnswerService;

    @Autowired
    private WritingAnswerService writingAnswerService;

    @GetMapping
    public String getTestSubmissions(Model model,
                                     @PathVariable(value = "courseId") Integer courseId,
                                     @PathVariable(value = "testId") Integer testId,
                                     @RequestParam Map<String, String> params) {
        List<TestAttempt> testAttempts = testAttemptService.getAllTestAttemptsByTestId(testId, params);
        if (testAttempts.isEmpty()) {
            model.addAttribute("msg_error", "No submissions found for this test.");
        } else {
            model.addAttribute("testAttempts", testAttempts);
        }

        // Prepare pagination
        long totalSubmissions = testAttemptService.countTestAttemptsByTestId(testId);
        Pagination pagination = paginationHelper.getPagination(params, totalSubmissions);

        Map<Integer, String> studentNames = testAttempts.stream().collect(
                Collectors.toMap(
                        TestAttempt::getId,
                        ta -> ta.getUserId() != null ? ta.getUserId().getUserId().getUsername() : "Unknown Student"
                )
        );

        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages());
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());
        model.addAttribute("courseId", courseId);
        model.addAttribute("testId", testId);
        model.addAttribute("studentNames", studentNames);
        return "dashboard/lecturer/submission/test_submission_list";
    }

    @GetMapping("/{submissionId}")
    public String getTestAttemptDetails(Model model,
                                        @PathVariable(value = "courseId") Integer courseId,
                                        @PathVariable(value = "testId") Integer testId,
                                        @PathVariable(value = "submissionId") Integer submissionId) {
        Optional<TestAttempt> optionalTestAttempt = testAttemptService.getTestAttemptById(submissionId);
        if (optionalTestAttempt.isEmpty()) {
            model.addAttribute("msg_error", "Test attempt not found.");
            return "dashboard/lecturer/submission/test_submission_list"; // Redirect to the submission list if attempt not found
        }

        List<Question> questions = questionService.getQuestionsByTest(testId);

        List<TestAttemptAnswer> testAttemptAnswers = testAttemptAnswerService.getTestAttemptAnswersByTestAttemptId(submissionId,null);

        Map<Question, Map<String,Object>> questionDetails = new HashMap<>();
        for (Question question : questions) {
            Map<String, Object> details = new HashMap<>();
            Optional<TestAttemptAnswer> studentAnswer = testAttemptAnswers.stream()
                    .filter(answer -> answer.getQuestionId().getId().equals(question.getId()))
                    .findFirst();
            studentAnswer.ifPresent(answer -> details.put("studentAnswer", answer.getAnswerText()));
            String questionType = question.getQuestionTypeId() != null ? question.getQuestionTypeId().getName() : "Unknown Type";
            details.put("questionType", questionType);
            if("multiple choice".equalsIgnoreCase(questionType)){
                List<MultipleChoiceAnswer> multipleChoiceAnswers = multipleChoiceAnswerService.getAnswersByQuestionId(question.getId());
                details.put("multipleChoiceAnswers", multipleChoiceAnswers);
            } else if ("writing".equalsIgnoreCase(questionType)) {
                WritingAnswer writingAnswer = writingAnswerService.getWritingAnswerByQuestionId(question.getId());
                details.put("writingAnswer", writingAnswer != null ? writingAnswer : "No answer provided");
            }
            questionDetails.put(question, details);
        }

        model.addAttribute("courseId", courseId);
        model.addAttribute("testId", testId);
        model.addAttribute("submissionId", submissionId);
        model.addAttribute("questionDetails", questionDetails);
        model.addAttribute("testAttempt", optionalTestAttempt.get());
        return "dashboard/lecturer/submission/test_submission_detail";
    }

    @PostMapping("/{submissionId}/update")
    public String updateTestAttempt(@ModelAttribute("testAttempt") TestAttempt testAttempt,
                                    @PathVariable(value = "courseId") Integer courseId,
                                    @PathVariable(value = "testId") Integer testId,
                                    @RequestParam Map<String, String> params,
                                    RedirectAttributes redirectAttributes) {
        if(testAttempt.getId() == null || testAttempt.getId() <= 0) {
            throw new IllegalArgumentException("Invalid test attempt ID");
        }

        TestAttempt updatedTestAttempt = testAttemptService.updateTestAttempt(testAttempt);

        if(updatedTestAttempt != null){
            redirectAttributes.addFlashAttribute("msg_success", "Test attempt updated successfully.");
        } else {
            redirectAttributes.addFlashAttribute("msg_error", "Failed to update test attempt. Please try again.");
        }


        return "redirect:/course/" + courseId + "/tests/test/" + testId + "/submissions/" + updatedTestAttempt.getId();
    }
}
