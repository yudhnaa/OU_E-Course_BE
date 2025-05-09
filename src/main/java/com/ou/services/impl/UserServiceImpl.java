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
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;
import java.util.logging.Logger;

/**
 *
 * @author yudhna
 */
@Service("userDetailService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private LocalizationService localizationService;

    @Override
    public User getUserById(int id) {
        return userRepo.getUserById(id).orElseThrow(() -> new NotFoundException(localizationService.getMessage("user.notFound", LocaleContextHolder.getLocale())));
    }

    @Override
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User u = userRepo.addUser(user);
        return u;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Fetch user from the database
        User user = userRepo.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        localizationService.getMessage("user.notFound", LocaleContextHolder.getLocale())));

        // Map roles to GrantedAuthority
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRoleId().getName()));

        // Return UserDetails object
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
//                user.getIsActive(),
//                true, // accountNonExpired
//                true, // credentialsNonExpired
//                true, // accountNonLocked
                authorities
        );
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
