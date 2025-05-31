package com.ou.controllers.restController;


import com.ou.dto.ExerciseAttemptDto;
import com.ou.mappers.ExerciseAttemptMapper;
import com.ou.pojo.*;
import com.ou.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/secure/courses/{courseId}/exercises/{exerciseId}")
public class RestExerciseAttemptController {
    @Autowired
    private ExerciseAttemptService exerciseAttemptService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExerciseAttemptMapper exerciseAttemptMapper;

    @Autowired
    private ExerciseScoreStatusService exerciseScoreStatusService;

    @Autowired
    private ExerciseService exerciseService;


    @GetMapping("/attempts")
    public ResponseEntity<List<ExerciseAttemptDto>> getExerciseAttempts(
            @PathVariable("courseId") Integer courseId,
            @PathVariable("exerciseId") Integer exerciseId,
            @AuthenticationPrincipal CustomUserDetails principal,
            @RequestParam Map<String, String> params
            ) {
        params.put("exerciseId", String.valueOf(exerciseId));
        Student student = studentService.getStudentByUserId(principal.getUser().getId());
        List<ExerciseAttempt> attempts = exerciseAttemptService.getExerciseAttemptsByStudentId(student.getId(), params);
        List<ExerciseAttemptDto> attemptDtos = attempts
                .stream()
                .map(attempt -> exerciseAttemptMapper.toDto(attempt))
                .toList();
        return new ResponseEntity<>(attemptDtos, HttpStatus.OK);
    }

    @PostMapping("/attempts/add")
    public ResponseEntity<ExerciseAttemptDto> addExerciseAttempt(
            @PathVariable("courseId") Integer courseId,
            @PathVariable("exerciseId") Integer exerciseId,
            @AuthenticationPrincipal CustomUserDetails principal,
            @RequestBody ExerciseAttemptDto exerciseAttemptDto) throws Exception {
        Student student = studentService.getStudentByUserId(principal.getUser().getId());
        Exercise exercise = exerciseService.getExerciseById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Exercise not found with ID: " + exerciseId));
        // Map DTO to entity
        ExerciseAttempt exerciseAttempt = exerciseAttemptMapper.toEntity(exerciseAttemptDto);
        exerciseAttempt.setStudentId(student);
        exerciseAttempt.setExerciseId(exercise);
        exerciseAttempt.setScoreByUserId(exercise.getCreatedByUserId());

        Optional<ExerciseScoreStatus> exerciseScoreStatus = exerciseScoreStatusService.getExerciseScoreStatusById(3);
        if (exerciseScoreStatus.isEmpty()) {
            throw new IllegalArgumentException("Exercise score status not found with ID: 3");
        }
        exerciseAttempt.setStatusId(exerciseScoreStatus.get());

        exerciseAttemptMapper.linkExerciseAttemptAnswerSet(exerciseAttempt);

        ExerciseAttempt addedAttempt = exerciseAttemptService.addExerciseAttempt(exerciseAttempt);


        ExerciseAttemptDto addedAttemptDto = exerciseAttemptMapper.toDto(addedAttempt);

        return new ResponseEntity<>(addedAttemptDto, HttpStatus.CREATED);
    }

}
