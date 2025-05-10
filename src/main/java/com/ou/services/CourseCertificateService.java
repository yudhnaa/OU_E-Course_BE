package com.ou.services;

import com.ou.pojo.CourseCertificate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CourseCertificateService {
    // Create operations
    CourseCertificate createCourseCertificate(CourseCertificate certificate) throws Exception;
    
    // Read operations
    List<CourseCertificate> getCourseCertificates(Map<String, String> params);
    Optional<CourseCertificate> getCourseCertificateById(Integer id);
    List<CourseCertificate> searchCourseCertificates(Map<String, String> filters, Map<String, String> params);
    List<CourseCertificate> getCertificatesByCourseStudentId(Integer courseStudentId, Map<String, String> params);
    
    // Update operations
    CourseCertificate updateCourseCertificate(CourseCertificate certificate) throws Exception;
    
    // Delete operations
    boolean deleteCourseCertificate(Integer id) throws Exception;
    
    // Count methods for pagination
    long countCourseCertificates();
    long countCertificatesByCourseStudentId(Integer courseStudentId);
    long countSearchResults(Map<String, String> filters);
}
