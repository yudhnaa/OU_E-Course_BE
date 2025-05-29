package com.ou.controllers.restController;

import com.ou.dto.StudentDto;
import com.ou.mappers.StudentMapper;
import com.ou.pojo.Student;
import com.ou.pojo.User;
import com.ou.services.StudentService;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/api")
public class RestStudentController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/course/students/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentDto>> getStudentsNotInCourse(
            @RequestParam(value = "courseId", required = true) Integer courseId,
            @RequestParam Map<String, String> params
    ) {

        Map<String, String> filters = new HashMap<>();
        filters.put("role", "user");
        if (params.containsKey("username")) {
            filters.put("username",params.get("username"));
            params.remove("username");
        }
        if (params.containsKey("courseId")) {
            filters.put("notInCourse", params.get("courseId"));
            params.remove("courseId");
        }
        filters.put("notInCourse", String.valueOf(courseId));

        List<Student> students = studentService.searchStudents(filters, null);

        List<StudentDto> studentDtos = students.stream()
                .map(s -> studentMapper.toDto(s))
                .collect(Collectors.toList());

        return new ResponseEntity<>(studentDtos, HttpStatus.OK);
    }
}
