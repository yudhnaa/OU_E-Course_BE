package com.ou.controllers.restController;


import com.ou.dto.ExerciseAttemptDto;
import com.ou.mappers.ExerciseAttemptMapper;
import com.ou.pojo.CustomUserDetails;
import com.ou.pojo.ExerciseAttempt;
import com.ou.pojo.Student;
import com.ou.services.ExerciseAttemptService;
import com.ou.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/secure/courses/{courseId}/exercises/{exerciseId}")
public class RestExerciseAttemptController {
    @Autowired
    private ExerciseAttemptService exerciseAttemptService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ExerciseAttemptMapper exerciseAttemptMapper;


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
            @RequestBody ExerciseAttemptDto exerciseAttemptDto) {
        Student student = studentService.getStudentByUserId(principal.getUser().getId());;
        ExerciseAttempt exerciseAttempt = exerciseAttemptMapper.toEntity(exerciseAttemptDto);
        ExerciseAttempt savedAttempt = exerciseAttemptService.addExerciseAttempt(exerciseAttempt);
        ExerciseAttemptDto savedAttemptDto = exerciseAttemptMapper.toDto(savedAttempt);
        return new ResponseEntity<>(savedAttemptDto, HttpStatus.CREATED);
    }

}
