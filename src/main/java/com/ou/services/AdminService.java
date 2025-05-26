package com.ou.services;

import com.ou.pojo.Admin;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AdminService {
    
    // Retrieve an admin by ID
    Admin getAdminById(int id);
    
    // Retrieve an admin by user ID
    Admin getAdminByUserId(int userId);
    
    // Add a new admin
    Admin addAdmin(Admin admin) throws IOException;
    
    // Update an existing admin
    Admin updateAdmin(Admin admin);
    
    // Delete an admin by ID
    boolean deleteAdmin(int id);
    
    // Retrieve a list of admins with optional pagination
    List<Admin> getAdmins(Map<String, String> params);
    
    // Search for admins based on filters and pagination
    List<Admin> searchAdmins(Map<String, String> filters, Map<String, String> params);
    
    // Retrieve admins associated with a specific course
    List<Admin> getAdminsByCourse(Integer courseId, Map<String, String> params);
    
    // Count total admins
    long countAdmins();
    
    // Count search results based on filters
    long countSearchResults(Map<String, String> filters);
    
    // Count admins by course
    long countAdminsByCourse(Integer courseId);
}
