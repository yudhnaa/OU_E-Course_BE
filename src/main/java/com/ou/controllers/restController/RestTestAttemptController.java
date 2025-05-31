package com.ou.controllers.restController;

import com.ou.dto.TestAttemptDto;
import com.ou.mappers.TestAttemptMapper;
import com.ou.pojo.CustomUserDetails;
import com.ou.pojo.Student;
import com.ou.pojo.Test;
import com.ou.pojo.TestAttempt;
import com.ou.services.StudentService;
import com.ou.services.TestAttemptService;
import com.ou.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/secure/courses/{courseId}/tests/{testId}")
public class RestTestAttemptController {
    @Autowired
    private TestAttemptService testAttemptService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TestAttemptMapper testAttemptMapper;

    @Autowired
    private TestService testService;

    @GetMapping("/attempts")
    public ResponseEntity<List<TestAttemptDto>> getTestAttempts(
            @PathVariable("courseId") Integer courseId,
            @PathVariable("testId") Integer testId,
            @AuthenticationPrincipal CustomUserDetails principal,
            @RequestParam Map<String, String> params) {
        params.put("testId", String.valueOf(testId));
        Student student = studentService.getStudentByUserId(principal.getUser().getId());
        List<TestAttempt> attempts = testAttemptService.getTestAttemptsByStudentId(student.getId(),params);
        List<TestAttemptDto> attemptDtos = attempts
                .stream()
                .map(attempt -> testAttemptMapper.toDto(attempt))
                .toList();
        return new ResponseEntity<>(attemptDtos, HttpStatus.OK);
    }

    @PostMapping("/attempts/add")
    public ResponseEntity<TestAttemptDto> addTestAttempt(
            @PathVariable("courseId") Integer courseId,
            @PathVariable("testId") Integer testId,
            @AuthenticationPrincipal CustomUserDetails principal,
            @RequestBody TestAttemptDto testAttemptDto) throws Exception {
        Student student = studentService.getStudentByUserId(principal.getUser().getId());
        Optional<Test> test = testService.getTestById(testId);
        if(test.isEmpty()) {
            throw new IllegalArgumentException("Test not found with ID: " + testId);
        }

        TestAttempt testAttempt = testAttemptMapper.toEntity(testAttemptDto);
        testAttempt.setUserId(student);
        testAttempt.setTestId(test.get());
        testAttemptMapper.linkTestAttemptAnswerSet(testAttempt);

        TestAttempt savedAttempt = testAttemptService.addTestAttempt(testAttempt);
        TestAttemptDto savedAttemptDto = testAttemptMapper.toDto(savedAttempt);
        return new ResponseEntity<>(savedAttemptDto, HttpStatus.CREATED);
    }




}
