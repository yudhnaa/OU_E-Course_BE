package com.ou.controllers.restController;

import com.ou.dto.QuestionDto;
import com.ou.mappers.QuestionMapper;
import com.ou.pojo.Question;
import com.ou.services.MultipleChoiceAnswerService;
import com.ou.services.QuestionService;
import com.ou.services.WritingAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
public class RestQuestionController {
    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private MultipleChoiceAnswerService multipleChoiceAnswerService;

    @Autowired
    private WritingAnswerService writingAnswerService;

    @GetMapping("/api/courses/{courseId}/exercises/{exerciseId}/questions")
    public ResponseEntity<List<QuestionDto>> getQuestionsByExerciseId(@PathVariable("exerciseId") Integer exerciseId) {
        List<Question> questions = questionService.getQuestionsByExercise(exerciseId);

        List<QuestionDto> questionDtos = questions.stream()
                .map(q -> {
                    QuestionDto dto = questionMapper.toDto(q); // giờ toDto chỉ trả ID và content
                    String type = q.getQuestionTypeId().getName();

                    if ("multiple choice".equals(type)) {
                        dto.setMultipleChoiceAnswerSet(
                                multipleChoiceAnswerService.getAnswersByQuestionId(q.getId()).stream()
                                        .map(mc -> new QuestionDto.MultipleChoiceAnswerDto1(mc.getId(), mc.getContent(), mc.getIsCorrect()))
                                        .collect(Collectors.toSet())
                        );
                    } else if ("writing".equals(type)) {
                        var w = writingAnswerService.getWritingAnswerByQuestionId(q.getId());
                        if (w != null) {
                            dto.setWritingAnswerSet(Set.of(new QuestionDto.WritingAnswerDto1(w.getId(), w.getContent())));
                        }
                    }
                    return dto;
                })
                .toList();

        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }

    @GetMapping("/api/courses/{courseId}/tests/{testId}/questions")
    public ResponseEntity<List<QuestionDto>> getQuestionsByTestId(@PathVariable("testId") Integer testId) {
        List<Question> questions = questionService.getQuestionsByTest(testId);

        List<QuestionDto> questionDtos = questions.stream()
                .map(q -> {
                    QuestionDto dto = questionMapper.toDto(q);
                    String type = q.getQuestionTypeId().getName();

                    if ("multiple choice".equals(type)) {
                        dto.setMultipleChoiceAnswerSet(
                                multipleChoiceAnswerService.getAnswersByQuestionId(q.getId()).stream()
                                        .map(mc -> new QuestionDto.MultipleChoiceAnswerDto1(mc.getId(), mc.getContent(), mc.getIsCorrect()))
                                        .collect(Collectors.toSet())
                        );
                    } else if ("writing".equals(type)) {
                        var w = writingAnswerService.getWritingAnswerByQuestionId(q.getId());
                        if (w != null) {
                            dto.setWritingAnswerSet(Set.of(new QuestionDto.WritingAnswerDto1(w.getId(), w.getContent())));
                        }
                    }
                    return dto;
                })
                .toList();

        return new ResponseEntity<>(questionDtos, HttpStatus.OK);
    }




}
