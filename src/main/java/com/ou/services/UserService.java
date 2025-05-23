/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.exceptions.NotFoundException;
import com.ou.pojo.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author yudhna
 */

public interface UserService {

    // Retrieve a user by ID with localization support
    User getUserById(int id);

    // Add a new user
    User addUser(User user) throws IOException;

    // Update an existing user
    User updateUser(User user);

    // Delete a user by ID
    boolean deleteUser(int id);

    // Retrieve a list of users with optional filters and pagination
    List<User> getUsers(Map<String, String> params);

    // Search for users based on filters and pagination
    List<User> searchUsers(Map<String, String> filters, Map<String, String> params);

    // Retrieve a user by username
    Optional<User> getUserByUsername(String username);

    // Retrieve a user by email
    Optional<User> getUserByEmail(String email);

    // Retrieve active users with pagination
    List<User> getActiveUsers(Map<String, String> params);

    // Retrieve users by role with pagination
    List<User> getUsersByRole(Integer roleId, Map<String, String> params);

    // Count total users
    long countUsers(String locale);

    // Count active users
    long countActiveUsers(String locale);

    // Count users by role
    long countUsersByRole(Integer roleId);

    // Count search results based on filters
    long countSearchResults(Map<String, String> filters);
}