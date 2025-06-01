package com.ou.services.impl;

import com.ou.helpers.GoogleDriveHelper;
import com.ou.pojo.CourseCertificate;
import com.ou.pojo.CourseStudent;
import com.ou.repositories.CourseCertificateRepository;
import com.ou.services.CourseCertificateService;
import com.ou.services.LocalizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class CourseCertificateServiceImpl implements CourseCertificateService {

    @Autowired
    private CourseCertificateRepository courseCertificateRepository;
    @Autowired
    private GoogleDriveHelper googleDriveHelper;
    @Autowired
    private LocalizationService localizationService;

    @Override
    public CourseCertificate createCourseCertificate(CourseCertificate certificate, File certificateFile) throws Exception {
        // Validate required fields
        validateCertificate(certificate);

        Map<String, String> uploadMetaData = googleDriveHelper.uploadFile(certificateFile);

        // Set the download link from Google Drive metadata
        certificate.setDownloadLink(uploadMetaData.get("url"));

        // Additional business validation
//        validateDownloadLink(certificate.getDownloadLink());
        
        // Ensure courseStudentId is provided
        if (certificate.getCourseStudentId() == null || certificate.getCourseStudentId().getId() == null) {
            throw new IllegalArgumentException(localizationService.getMessage("data.invalid", LocaleContextHolder.getLocale()));
        }
        
        return courseCertificateRepository.addCourseCertificate(certificate);
    }

    @Override
    public CourseCertificate createCourseCertificate(CourseStudent courseStudent, String downloadLink) throws Exception {

        CourseCertificate certificate = new CourseCertificate();
        certificate.setCourseStudentId(courseStudent);
        certificate.setDownloadLink(downloadLink);

        // Validate required fields
        validateCertificate(certificate);

        // Additional business validation
//        validateDownloadLink(certificate.getDownloadLink());


        // Ensure courseStudentId is provided
        if (certificate.getCourseStudentId() == null || certificate.getCourseStudentId().getId() == null) {
            throw new IllegalArgumentException(localizationService.getMessage("data.invalid", LocaleContextHolder.getLocale()));
        }

        return courseCertificateRepository.addCourseCertificate(certificate);
    }

    @Override
    public List<CourseCertificate> getCourseCertificates(Map<String, String> params) {
        return courseCertificateRepository.getCourseCertificates(params);
    }

    @Override
    public Optional<CourseCertificate> getCourseCertificateById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Certificate ID cannot be null");
        }
        return courseCertificateRepository.getCourseCertificateById(id);
    }

    @Override
    public Optional<CourseCertificate> getCourseCertificateByIdAndStudentId(Integer certificateId, Integer studentId) {
        if (certificateId == null) {
            throw new IllegalArgumentException("Certificate ID cannot be null");
        }
        return courseCertificateRepository.getCourseCertificateByIdAndStudentId(certificateId, studentId);
    }

    @Override
    public List<CourseCertificate> searchCourseCertificates(Map<String, String> filters, Map<String, String> params) {
        return courseCertificateRepository.searchCourseCertificates(filters, params);
    }

    @Override
    public List<CourseCertificate> getCertificatesByCourseStudentId(Integer courseStudentId, Map<String, String> params) {
        if (courseStudentId == null) {
            throw new IllegalArgumentException("Course student ID cannot be null");
        }
        return courseCertificateRepository.getCertificatesByCourseStudentId(courseStudentId, params);
    }

    @Override
    public CourseCertificate updateCourseCertificate(CourseCertificate certificate, File certificateFile) throws Exception {
        // Check if certificate exists
        if (certificate.getId() == null) {
            throw new IllegalArgumentException("Certificate ID cannot be null when updating");
        }

        Optional<CourseCertificate> existingCertificate = courseCertificateRepository.getCourseCertificateById(certificate.getId());
        if (!existingCertificate.isPresent()) {
            throw new Exception("Certificate with ID " + certificate.getId() + " not found");
        }

        Map<String, String> uploadMetaData = googleDriveHelper.uploadFile(certificateFile);

        // Set the download link from Google Drive metadata
        certificate.setDownloadLink(uploadMetaData.get("url"));

        // Validate fields
        validateCertificate(certificate);
        validateDownloadLink(certificate.getDownloadLink());

        // Ensure courseStudentId is provided and valid
        if (certificate.getCourseStudentId() == null || certificate.getCourseStudentId().getId() == null) {
            throw new IllegalArgumentException("Course student ID is required");
        }

        return courseCertificateRepository.updateCourseCertificate(certificate);
    }

//    @Override
//    public CourseCertificate updateCourseCertificate(CourseCertificate certificate) throws Exception {
//        // Check if certificate exists
//        if (certificate.getId() == null) {
//            throw new IllegalArgumentException("Certificate ID cannot be null when updating");
//        }
//
//        Optional<CourseCertificate> existingCertificate = courseCertificateRepository.getCourseCertificateById(certificate.getId());
//        if (!existingCertificate.isPresent()) {
//            throw new Exception("Certificate with ID " + certificate.getId() + " not found");
//        }
//
//        // Validate fields
//        validateCertificate(certificate);
//        validateDownloadLink(certificate.getDownloadLink());
//
//        // Ensure courseStudentId is provided and valid
//        if (certificate.getCourseStudentId() == null || certificate.getCourseStudentId().getId() == null) {
//            throw new IllegalArgumentException("Course student ID is required");
//        }
//
//        return courseCertificateRepository.updateCourseCertificate(certificate);
//    }

    @Override
    public boolean deleteCourseCertificate(Integer id) throws Exception {
        if (id == null) {
            throw new IllegalArgumentException("Certificate ID cannot be null");
        }
        
        // Check if certificate exists before deletion
        Optional<CourseCertificate> existingCertificate = courseCertificateRepository.getCourseCertificateById(id);
        if (!existingCertificate.isPresent()) {
            throw new Exception("Certificate with ID " + id + " not found");
        }
        
        return courseCertificateRepository.deleteCourseCertificate(id);
    }

    @Override
    public long countCourseCertificates() {
        return courseCertificateRepository.countCourseCertificates();
    }

    @Override
    public long countCertificatesByCourseStudentId(Integer courseStudentId) {
        if (courseStudentId == null) {
            throw new IllegalArgumentException("Course student ID cannot be null");
        }
        return courseCertificateRepository.countCertificatesByCourseStudentId(courseStudentId);
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        return courseCertificateRepository.countSearchResults(filters);
    }
    
    // Private validation methods
    private void validateCertificate(CourseCertificate certificate) {
        if (certificate == null) {
            throw new IllegalArgumentException("Certificate cannot be null");
        }
//
//        if (certificate.getDownloadLink() == null || certificate.getDownloadLink().trim().isEmpty()) {
//            throw new IllegalArgumentException("Download link is required");
//        }
    }
    
    private void validateDownloadLink(String downloadLink) {
        if (downloadLink == null || downloadLink.trim().isEmpty()) {
            throw new IllegalArgumentException("Download link cannot be empty");
        }
        
        // Validate download link format (basic URL validation)
        if (!downloadLink.startsWith("http://") && !downloadLink.startsWith("https://")) {
            throw new IllegalArgumentException("Download link must be a valid URL starting with http:// or https://");
        }
        
        // Check length constraints
        if (downloadLink.length() > 255) {
            throw new IllegalArgumentException("Download link cannot exceed 255 characters");
        }
    }
}
