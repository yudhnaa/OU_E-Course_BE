package com.ou.repositories;

import com.ou.pojo.Admin;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AdminRepository {
    // Create operations
    Admin addAdmin(Admin admin);
    
    // Read operations with pagination
    List<Admin> getAdmins(Map<String, String> params);
    List<Admin> searchAdmins(Map<String, String> filters, Map<String, String> params);
    
    // Return an Optional<Admin> to handle cases where admin is not found
    Optional<Admin> getAdminById(Integer id);
    Optional<Admin> getAdminByUserId(Integer userId);
    
    // Update operation
    Admin updateAdmin(Admin admin);
    
    // Delete operation
    boolean deleteAdmin(Integer id);
    
    // Count methods for pagination
    long countAdmins();
    long countSearchResults(Map<String, String> filters);
    
    // Course related methods
    List<Admin> getAdminsByCourse(Integer courseId, Map<String, String> params);
    long countAdminsByCourse(Integer courseId);
}
