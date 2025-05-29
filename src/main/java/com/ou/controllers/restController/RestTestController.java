package com.ou.controllers.restController;

import com.ou.dto.TestDto;
import com.ou.mappers.TestMapper;
import com.ou.pojo.Test;
import com.ou.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/courses/{courseId}/")
public class RestTestController {
    @Autowired
    private TestService testService;

    @Autowired
    private TestMapper mapper;

    @GetMapping("/tests")
    public ResponseEntity<List<TestDto>> getTests(
            @PathVariable(value = "courseId") Integer courseId,
            @RequestParam Map<String, String> params) {
        params.put("courseId", String.valueOf(courseId));
        List<Test> tests = testService.getAllTests(params);

        List<TestDto> testDtos = tests.stream()
                .map(test -> mapper.toDto(test))
                .toList();
        return new ResponseEntity<>(testDtos, HttpStatus.OK);
    }

    @GetMapping("/tests/{testId}")
    public ResponseEntity<TestDto> getTestById(
            @PathVariable(value = "courseId") Integer courseId,
            @PathVariable(value = "testId") Integer testId) {
        Optional<Test> test = testService.getTestById(testId);
        if (test.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        TestDto testDto = mapper.toDto(test.get());
        return new ResponseEntity<>(testDto, HttpStatus.OK);
    }

}
