package com.ou.controllers.webController.adminController;

import com.ou.dto.CourseCertificateDto;
import com.ou.helpers.PaginationHelper;
import com.ou.mappers.CourseCertificateMapper;
import com.ou.pojo.CourseCertificate;
import com.ou.services.CertificateService;
import com.ou.services.CourseCertificateService;
import com.ou.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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


    public StudentCertificateController(CourseCertificateService courseCertificateService, CourseCertificateMapper courseCertificateMapper, PaginationHelper paginationHelper) {
        this.courseCertificateService = courseCertificateService;
        this.courseCertificateMapper = courseCertificateMapper;
        this.paginationHelper = paginationHelper;
    }

    @GetMapping("/student-certificates")
    public String studentCertificates(
            Model model,
            @RequestParam Map<String, String> params,
            RedirectAttributes redirectAttributes
            ) {

        // Pagination
        long totalItems = courseCertificateService.countSearchResults(params);
        Pagination pagination = paginationHelper.getPagination(params, totalItems);

        List<CourseCertificateDto> courseCertificates = courseCertificateService.getCourseCertificates(params).stream()
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
            certificateService.generatePdf(courseCertificate.get());
            redirectAttributes.addFlashAttribute("successMessage", "Certificate generated successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to generate certificate: " + e.getMessage());
        }
        return "redirect:/admin/student-certificates";
    }

}
