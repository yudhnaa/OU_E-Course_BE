package com.ou.controllers.restController;

import com.ou.dto.StudentDto;
import com.ou.mappers.StudentMapper;
import com.ou.pojo.Student;
import com.ou.pojo.User;
import com.ou.services.StudentService;
import com.ou.services.UserDetailService;
import com.ou.services.UserService;
import com.ou.utils.JwtUtils;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/api")
@CrossOrigin
public class RestAuthenticationController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDetailService userDetailService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentMapper studentMapper;

//    @PostMapping("/users")
//    public ResponseEntity<User> create(@RequestParam Map<String, String> params,
//                                       @RequestParam("avatar") MultipartFile avatar) {
//        User u = this.userService.addUser(params, avatar);
//
//        return new ResponseEntity<>(u, HttpStatus.CREATED);
//    }

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

    @RequestMapping("/secure/profile")
    @ResponseBody
    public ResponseEntity<StudentDto> getProfile(Principal principal) {
        Student student = studentService.getStudentById(1);

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentMapper.toDto(student), HttpStatus.OK);
    }
}
