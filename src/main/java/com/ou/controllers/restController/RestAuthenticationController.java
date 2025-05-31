package com.ou.controllers.restController;

import com.ou.dto.StudentDto;
import com.ou.dto.StudentSignUpFormDto;
import com.ou.mappers.StudentMapper;
import com.ou.mappers.StudentSignUpFormMapper;
import com.ou.pojo.CustomUserDetails;
import com.ou.pojo.Student;
import com.ou.pojo.User;
import com.ou.services.StudentService;
import com.ou.services.UserDetailService;
import com.ou.services.UserRoleService;
import com.ou.services.UserService;
import com.ou.utils.JwtUtils;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
public class RestAuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private StudentSignUpFormMapper studentSignUpFormMapper;
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/user/create")
    public ResponseEntity<StudentDto> create(
            @ModelAttribute StudentSignUpFormDto studentSignUpFormDto,
            BindingResult result
    ) throws IOException {

        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(null);
        }

        // Convert DTO to User entity

        Student student = studentSignUpFormMapper.toEntity(studentSignUpFormDto);
        // set role to "ROLE_STUDENT"
        student.getUserId().setUserRoleId(userRoleService.getUserRoleByName("ROLE_STUDENT"));

        User createdUser = userService.addUser(student.getUserId());
        if (createdUser == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        // Create student from user
        student.setUserId(createdUser);
        Student createdStudent = studentService.addStudent(student);
        if (createdStudent == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

        // Map to DTO
        StudentDto studentDto = studentMapper.toDto(createdStudent);

        return new ResponseEntity<>(studentDto, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User u) {

        if (userDetailService.authenticate(u.getUsername(), u.getPassword())) {
            try {
                String token = JwtUtils.generateToken(u.getUsername());
                return ResponseEntity.ok().body(Collections.singletonMap("token", token));
            } catch (Exception e) {
                return ResponseEntity.status(500).body("Lỗi khi tạo JWT");
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Sai thông tin đăng nhập");
    }
}
