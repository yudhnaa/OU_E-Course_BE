/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.services.impl;

import com.ou.exceptions.NotFoundException;
import com.ou.pojo.User;
import com.ou.repositories.UserRepository;
import com.ou.services.LocalizationService;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author yudhna
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private LocalizationService localizationService;

    @Override
    public User getUserById(int id, Locale locale) {
        return userRepo.getUserById(id).orElseThrow(() -> new NotFoundException(localizationService.getMessage("user.notFound", locale)));
    }

    @Override
    public User addUser(User user) {
        return null;
    }

    @Override
    public User updateUser(User user) {
        return null;
    }

    @Override
    public boolean deleteUser(int id) {
        return false;
    }

    @Override
    public List<User> getUsers(Map<String, String> params) {
        return List.of();
    }

    @Override
    public List<User> searchUsers(Map<String, String> filters, Map<String, String> params) {
        return List.of();
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<User> getActiveUsers(Map<String, String> params) {
        return List.of();
    }

    @Override
    public List<User> getUsersByRole(Integer roleId, Map<String, String> params) {
        return List.of();
    }

    @Override
    public long countUsers(String locale) {
        return 0;
    }

    @Override
    public long countActiveUsers(String locale) {
        return 0;
    }

    @Override
    public long countUsersByRole(Integer roleId) {
        return 0;
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        return 0;
    }

}
