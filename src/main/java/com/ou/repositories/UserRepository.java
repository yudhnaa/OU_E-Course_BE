/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.repositories;

import com.ou.pojo.User;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author yudhna
 */
public interface UserRepository {
    // Create operations
    User addUser(User user);

    // Read operations with pagination
    List<User> getUsers(Map<String, String> params);
    List<User> searchUsers(Map<String, String> filters, Map<String, String> params);

    // Return an Optional<User> to handle cases where the user is not found to avoid null checks
    Optional<User> getUserById(Integer id);
    Optional<User> getUserByUsername(String username);
    Optional<User> getUserByEmail(String email);
    List<User> getActiveUsers(Map<String, String> params);
    List<User> getUsersByRole(Integer roleId, Map<String, String> params);

    // Update operation
    User updateUser(User user);

    // Delete operation
    boolean deleteUser(Integer id);

    // Count methods for pagination
    long countUsers(String locale);
    long countActiveUsers(String locale);
    long countUsersByRole(Integer roleId);
    long countSearchResults(Map<String, String> filters);
}
