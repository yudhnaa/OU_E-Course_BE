package com.ou.controllers.restController;


import com.ou.dto.ExerciseDto;
import com.ou.mappers.ExerciseMapper;
import com.ou.pojo.Exercise;
import com.ou.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/courses/{courseId}/")
public class RestExerciseController {
    @Autowired
    private ExerciseMapper exerciseMapper;

    @Autowired
    private ExerciseService exerciseService;

    @GetMapping("/exercises")
    public ResponseEntity<List<ExerciseDto>> getExercises(@PathVariable(value = "courseId") Integer courseId,
                                                          @RequestParam Map<String, String> params) {
        params.put("courseId", String.valueOf(courseId));
        List<Exercise> exercises = exerciseService.getExercises(params);
        List<ExerciseDto> exerciseDtos = exercises.stream()
                .map(e -> exerciseMapper.toDto(e))
                .toList();
        return new ResponseEntity<>(exerciseDtos, HttpStatus.OK);
    }

    @GetMapping("/exercises/{exerciseId}")
    public ResponseEntity<ExerciseDto> getExerciseById(@PathVariable(value = "courseId") Integer courseId,
                                                       @PathVariable(value = "exerciseId") Integer exerciseId) throws Exception {
        Optional<Exercise> exercise = exerciseService.getExerciseById(exerciseId);
        if (exercise.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ExerciseDto exerciseDto = exerciseMapper.toDto(exercise.get());
        return new ResponseEntity<>(exerciseDto, HttpStatus.OK);
    }



}
