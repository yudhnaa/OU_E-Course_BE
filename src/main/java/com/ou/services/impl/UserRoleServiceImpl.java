package com.ou.services.impl;

import com.ou.pojo.UserRole;
import com.ou.repositories.UserRoleRepository;
import com.ou.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    @Transactional(readOnly = true)
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.getAllUserRoles();
    }

    @Override
    @Transactional(readOnly = true)
    public UserRole getUserRoleById(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("User Role ID cannot be null");
        }
        return userRoleRepository.getUserRoleById(id);
    }

    @Override
    public UserRole addUserRole(UserRole userRole) {
        // Validate user role before adding
        if (!isValidUserRole(userRole)) {
            throw new IllegalArgumentException("Invalid user role data");
        }
        
        // Check for duplicate name
        if (userRoleRepository.getUserRoleByName(userRole.getName()) != null) {
            throw new IllegalArgumentException("Role name already exists");
        }
        
        return userRoleRepository.addUserRole(userRole);
    }

    @Override
    public UserRole updateUserRole(UserRole userRole) {
        // Check if user role exists
        if (userRole.getId() == null || userRoleRepository.getUserRoleById(userRole.getId()) == null) {
            throw new IllegalArgumentException("User Role not found");
        }
        
        // Validate user role data
        if (!isValidUserRole(userRole)) {
            throw new IllegalArgumentException("Invalid user role data");
        }
        
        // Check for duplicate name (excluding this role)
        UserRole existingRole = userRoleRepository.getUserRoleByName(userRole.getName());
        if (existingRole != null && !existingRole.getId().equals(userRole.getId())) {
            throw new IllegalArgumentException("Role name already exists");
        }
        
        return userRoleRepository.updateUserRole(userRole);
    }

    @Override
    public boolean deleteUserRole(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("User Role ID cannot be null");
        }
        
        // Check if user role exists
        UserRole userRole = userRoleRepository.getUserRoleById(id);
        if (userRole == null) {
            throw new IllegalArgumentException("User Role not found");
        }
        
        // Check if the role has associated users
        if (userRole.getUserSet() != null && !userRole.getUserSet().isEmpty()) {
            throw new IllegalArgumentException("Cannot delete role with associated users");
        }
        
        return userRoleRepository.deleteUserRole(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserRole getUserRoleByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Role name cannot be empty");
        }
        return userRoleRepository.getUserRoleByName(name);
    }
    
    // Helper validation methods
    private boolean isValidUserRole(UserRole userRole) {
        if (userRole == null) {
            return false;
        }

        if (userRole.getName() == null || userRole.getName().trim().isEmpty()) {
            return false;
        }

        if (userRole.getName().length() > 50) {
            return false;
        }
        
        return true;
    }
}
