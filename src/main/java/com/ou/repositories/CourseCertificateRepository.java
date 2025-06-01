package com.ou.repositories;

import com.ou.pojo.CourseCertificate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CourseCertificateRepository {
    // Create operations
    CourseCertificate addCourseCertificate(CourseCertificate certificate);

    // Read operations with pagination
    List<CourseCertificate> getCourseCertificates(Map<String, String> params);
    List<CourseCertificate> searchCourseCertificates(Map<String, String> filters, Map<String, String> params);
    
    // Return an Optional<CourseCertificate> to handle cases where the certificate is not found
    Optional<CourseCertificate> getCourseCertificateById(Integer id);
    Optional<CourseCertificate> getCourseCertificateByIdAndStudentId(Integer certificateId, Integer studentId);
    
    // Get certificates by course student ID
    List<CourseCertificate> getCertificatesByCourseStudentId(Integer courseStudentId, Map<String, String> params);

    // Update operation
    CourseCertificate updateCourseCertificate(CourseCertificate certificate);

    // Delete operation
    boolean deleteCourseCertificate(Integer id);

    // Count methods for pagination
    long countCourseCertificates();
    long countCertificatesByCourseStudentId(Integer courseStudentId);
    long countSearchResults(Map<String, String> filters);
}
