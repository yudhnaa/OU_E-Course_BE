package com.ou.controllers.restController;

import com.ou.dto.StudentDto;
import com.ou.mappers.StudentMapper;
import com.ou.pojo.CustomUserDetails;
import com.ou.pojo.Student;
import com.ou.pojo.User;
import com.ou.services.StudentService;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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

    @GetMapping("/secure/profile")
    @ResponseBody
    public ResponseEntity<StudentDto> getProfile(
            @AuthenticationPrincipal CustomUserDetails principal
    ) {

        Student student = studentService.getStudentByUserId(principal.getUser().getId());

        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(studentMapper.toDto(student), HttpStatus.OK);
    }

    @PostMapping("/secure/profile")
    @ResponseBody
    public ResponseEntity<StudentDto> updateProfile(
            @RequestParam("userIdFirstName") String firstName,
            @RequestParam("userIdLastName") String lastName,
            @RequestParam("userIdEmail") String email,
            @RequestParam("userIdBirthday") String birthday,
            @RequestParam(value = "avatarFile", required = false) MultipartFile avatar,
            @AuthenticationPrincipal CustomUserDetails principal
    ){
        Student existingStudent = studentService.getStudentByUserId(principal.getUser().getId());
        if (existingStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        existingStudent.getUserId().setFirstName(firstName);
        existingStudent.getUserId().setLastName(lastName);
        existingStudent.getUserId().setEmail(email);
        existingStudent.getUserId().setBirthday(LocalDate.parse(birthday));
        existingStudent.getUserId().setAvatarFile(avatar);

        // Update the user info
        try {
            User updatedUser = userService.updateUser(existingStudent.getUserId());

            if (updatedUser == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>( HttpStatus.OK);
    }

    @PostMapping("/secure/change-password")
    @ResponseBody
    public ResponseEntity<StudentDto> updateProfilePassword(
            @RequestBody Map<String, String> params,
            @AuthenticationPrincipal CustomUserDetails principal
    ){
        Student existingStudent = studentService.getStudentByUserId(principal.getUser().getId());
        if (existingStudent == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String newPassword = params.get("newPassword");

        existingStudent.getUserId().setPassword(newPassword);

        // Update the user info
        try {
            User updatedUser = userService.updateUser(existingStudent.getUserId());

            if (updatedUser == null) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>( HttpStatus.OK);
    }


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
