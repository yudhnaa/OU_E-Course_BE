package com.ou.controllers.restController;

import com.ou.dto.CourseCertificateDto;
import com.ou.mappers.CourseCertificateMapper;
import com.ou.pojo.CourseCertificate;
import com.ou.pojo.CustomUserDetails;
import com.ou.pojo.Student;
import com.ou.services.CourseCertificateService;
import com.ou.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/secure/certificates")
public class RestCourseCertificateController {

    @Autowired
    private CourseCertificateService courseCertificateService;

    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseCertificateMapper courseCertificateMapper;

//     Get all certificates for the authenticated student
    @GetMapping
    public ResponseEntity<Map<String, Object>> getStudentCertificates(
            @RequestParam Map<String, String> params,
            @AuthenticationPrincipal CustomUserDetails principal) {

        try {
            Student student = studentService.getStudentByUserId(principal.getUser().getId());

            // Get certificates for this student
            List<CourseCertificate> certificates = courseCertificateService.getCourseCertificates(params);

            Map<String, Object> response = new HashMap<>();
            if (certificates.isEmpty()) {
                response.put("success", false);
                response.put("message", "No certificates found for this student.");
                return ResponseEntity.ok(response);
            }

            List<CourseCertificateDto> courseCertificateDtos = certificates.stream().map(courseCertificateMapper::toDto).toList();

            response.put("success", true);
            response.put("certificates", courseCertificateDtos);
            response.put("studentId", student.getId());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Failed to get certificates: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

//     Get certificates for a specific course
    @GetMapping("/course/{courseId}")
    public ResponseEntity<Map<String, Object>> getCertificatesByCourse(
            @PathVariable("courseId") Integer courseId,
            @RequestParam Map<String, String> params,
            @AuthenticationPrincipal CustomUserDetails principal) {

        try {
            Student student = studentService.getStudentByUserId(principal.getUser().getId());

            // This would need implementation in CourseCertificateService to filter by course and student
            // For now, using existing method with parameters
            params.put("courseId", courseId.toString());
            params.put("studentId", student.getId().toString());

            List<CourseCertificate> certificates = courseCertificateService.getCourseCertificates(params);
            List<CourseCertificateDto> courseCertificateDtos = certificates.stream().map(courseCertificateMapper::toDto).toList();

            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("certificates", courseCertificateDtos);
            response.put("courseId", courseId);
            response.put("studentId", student.getId());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Failed to get certificates for course: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

//   Get a specific certificate by ID
    @GetMapping("/{certificateId}")
    public ResponseEntity<Map<String, Object>> getCertificateById(
            @PathVariable("certificateId") Integer certificateId,
            @AuthenticationPrincipal CustomUserDetails principal) {

        try {
            Student student = studentService.getStudentByUserId(principal.getUser().getId());

            Optional<CourseCertificate> certificateOpt = courseCertificateService.getCourseCertificateByIdAndStudentId(certificateId, student.getId());

            if (certificateOpt.isEmpty()) {
                Map<String, Object> errorResponse = new HashMap<>();
                errorResponse.put("success", false);
                errorResponse.put("error", "Certificate not found");
                return ResponseEntity.notFound().build();
            }

            CourseCertificateDto certificateDto = courseCertificateMapper.toDto(certificateOpt.get());


            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("certificate", certificateDto);

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("success", false);
            errorResponse.put("error", "Failed to get certificate: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
