package com.ou.controllers.webController.adminController;

import com.ou.dto.CourseCertificateDto;
import com.ou.exceptions.NotFoundException;
import com.ou.helpers.PaginationHelper;
import com.ou.mappers.CourseCertificateMapper;
import com.ou.pojo.CourseCertificate;
import com.ou.pojo.CourseStudent;
import com.ou.pojo.CustomUserDetails;
import com.ou.pojo.Lecturer;
import com.ou.services.*;
import com.ou.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequestMapping("/admin")
@Controller
public class StudentCertificateController {

    @Autowired
    private CourseCertificateService courseCertificateService;
    @Autowired
    private CourseCertificateMapper courseCertificateMapper;
    @Autowired
    private PaginationHelper paginationHelper;
    @Autowired
    private CertificateService certificateService;
    @Autowired
    private LecturerService lecturerService;
    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private CourseStudentService courseStudentService;

    @GetMapping("/student-certificates")
    public String studentCertificates(
            Model model,
            @RequestParam Map<String, String> params,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal CustomUserDetails principal
            ) {

        if (principal.getUser().getUserRoleId().getName().contains("LECTURER")) {
            Lecturer lecturer = lecturerService.getLecturerByUserId(principal.getUser().getId())
                    .orElseThrow(() -> new NotFoundException(localizationService.getMessage("lecturer.notFound", LocaleContextHolder.getLocale())));

            params.put("lecturerId", lecturer.getId().toString());
        }

        // Pagination
        long totalItems = courseCertificateService.countSearchResults(params);
        Pagination pagination = paginationHelper.getPagination(params, totalItems);

        List<CourseCertificateDto> courseCertificates = courseCertificateService.searchCourseCertificates(params,params).stream()
                .map(courseCertificateMapper::toDto).toList();

        model.addAttribute("courseCertificates", courseCertificates);

        model.addAttribute("currentPage", pagination.getCurrentPage());
        model.addAttribute("totalPages", pagination.getTotalPages() > 0 ? pagination.getTotalPages() : 1);
        model.addAttribute("startIndex", pagination.getStartIndex());
        model.addAttribute("endIndex", pagination.getEndIndex());
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("filterName", params.getOrDefault("name", " "));

        return "/dashboard/admin/certificate/certificate_list";
    }

    @GetMapping("/student-certificates/generate")
    public String generateCertificate(
//            @RequestParam("id") Long id,
            RedirectAttributes redirectAttributes
    ) {
        try {
            Optional<CourseCertificate> courseCertificate = courseCertificateService.getCourseCertificateById(1);
            File generatedFile = certificateService.generatePdf(courseCertificate.get());

            int courseStudentId = 3; // This should be dynamically set based on the course student ID you want to generate the certificate for
            CourseStudent courseStudent = courseStudentService.getCourseStudentById(courseStudentId).orElseThrow(() -> new NotFoundException(localizationService.getMessage("courseStudent.notFound", LocaleContextHolder.getLocale())));
            courseStudent.setProgress(1);
            courseStudentService.updateCourseStudent(courseStudent);

            CourseCertificate certificate = new CourseCertificate();
            certificate.setCourseStudentId(courseStudent);

            CourseCertificate createdCert = courseCertificateService.createCourseCertificate(certificate, generatedFile);

            redirectAttributes.addFlashAttribute("successMessage", "Certificate generated successfully." + createdCert.getDownloadLink());
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to generate certificate: " + e.getMessage());
        }
        return "redirect:/admin/student-certificates";
    }

}
