/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.services.impl;

import com.cloudinary.Cloudinary;
import com.ou.exceptions.NotFoundException;
import com.ou.helpers.CloudinaryHelper;
import com.ou.pojo.User;
import com.ou.repositories.UserRepository;
import com.ou.services.LocalizationService;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author yudhna
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class.getName());

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private Cloudinary cloudinary;
    @Autowired
    private CloudinaryHelper cloudinaryHelper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public User getUserById(int id) {
        return userRepo.getUserById(id).orElseThrow(() -> new NotFoundException(localizationService.getMessage("user.notFound", LocaleContextHolder.getLocale())));
    }

    @Override
    public User addUser(User user) throws IOException {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        // Handle avatar upload with Cloudinary
        MultipartFile avatarFile = user.getAvatarFile();
        if (avatarFile != null) {
            Map<String, String> res = cloudinaryHelper.uploadMultipartFile(user.getAvatarFile());
            user.setAvatar((String) res.get("url"));
            user.setPublicId((String) res.get("publicId"));
        }

        return userRepo.addUser(user);
    }

    @Override
    public User updateUser(User user) {
        // Get existing user to check if we need to update the password
        Optional<User> existingUserOpt = userRepo.getUserById(user.getId());

        if (existingUserOpt.isPresent()) {
            User existingUser = existingUserOpt.get();

            // Only encode password if it has changed
            if (user.getPassword() != null && !user.getPassword().isEmpty()
                    && !bCryptPasswordEncoder.matches(user.getPassword(), existingUser.getPassword())){
                user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            } else {
                // Keep the existing password if not changed
                user.setPassword(existingUser.getPassword());
            }

            // Update the avatar if it's been changed
            processUserAvatar(user, existingUser);

            return userRepo.updateUser(user);
        } else {
            throw new NotFoundException(localizationService.getMessage("user.notFound", LocaleContextHolder.getLocale()));
        }
    }

    private void processUserAvatar(User user, User existingUser) {
        // If the avatar file is null, keep the existing avatar
        if (user.getAvatarFile() == null || user.getAvatarFile().isEmpty()) {
            user.setAvatar(existingUser.getAvatar());
            user.setPublicId(existingUser.getPublicId());
        } else {
            // Handle avatar upload with Cloudinary - this would be implemented in a separate method
            try {

                Map res = cloudinaryHelper.uploadMultipartFile(user.getAvatarFile());
                user.setAvatar((String) res.get("url"));
                user.setPublicId((String) res.get("publicId"));

            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Error uploading avatar for user ID: " + user.getId(), e);
                // Keep existing avatar on error
                user.setAvatar(existingUser.getAvatar());
                user.setPublicId(existingUser.getPublicId());
            }
        }
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
        List<User> users = userRepo.searchUsers(filters, params);
        return users;
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

    @Override
    public List<User> getActiveUsers(Map<String, String> params) {
        return userRepo.getActiveUsers(params);
    }

    @Override
    public List<User> getUsersByRole(Integer roleId, Map<String, String> params) {
        return userRepo.getUsersByRole(roleId, params);
    }

    @Override
    public long countUsers(String locale) {
        return userRepo.countUsers(locale);
    }

    @Override
    public long countActiveUsers(String locale) {
        return userRepo.countActiveUsers(locale);
    }

    @Override
    public long countUsersByRole(Integer roleId) {
        return userRepo.countUsersByRole(roleId);
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        return userRepo.countSearchResults(filters);
    }
}
