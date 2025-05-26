package com.ou.services.impl;

import com.ou.exceptions.NotFoundException;
import com.ou.pojo.Admin;
import com.ou.repositories.AdminRepository;
import com.ou.services.AdminService;
import com.ou.services.LocalizationService;

import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Logger;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    private static final Logger LOGGER = Logger.getLogger(AdminServiceImpl.class.getName());

    @Autowired
    private AdminRepository adminRepository;
    
    @Autowired
    private LocalizationService localizationService;
    @Autowired
    private UserService userService;

    @Override
    public Admin getAdminById(int id) {
        return adminRepository.getAdminById(id)
                .orElseThrow(() -> new NotFoundException(
                        localizationService.getMessage("admin.notFound", LocaleContextHolder.getLocale())));
    }

    @Override
    public Admin getAdminByUserId(int userId) {
        return adminRepository.getAdminByUserId(userId)
                .orElseThrow(() -> new NotFoundException(
                        localizationService.getMessage("admin.userNotFound", LocaleContextHolder.getLocale())));
    }

    @Override
    public Admin addAdmin(Admin admin) throws IOException {
        // Check if admin already exists
        Optional<Admin> existingAdmin = adminRepository.getAdminByUserId(admin.getId());
        if (existingAdmin.isPresent()) {
            throw new IllegalArgumentException(
                    localizationService.getMessage("user.create.error.alreadyExists", LocaleContextHolder.getLocale()));
        }

//        // Add User before adding Admin
//        if (admin.getUserId() == null || admin.getUserId().getId() == null) {
//            throw new IllegalArgumentException(
//                    localizationService.getMessage("admin.userRequired", LocaleContextHolder.getLocale()));
//        }
//        //check if user exists
//        if (adminRepository.getAdminByUserId(admin.getUserId().getId()).isPresent()) {
//            throw new IllegalArgumentException(
//                    localizationService.getMessage("user.create.error.alreadyExists", LocaleContextHolder.getLocale()));
//        }
//
//         userService.addUser(admin.getUserId());

        return adminRepository.addAdmin(admin);
    }

    @Override
    public Admin updateAdmin(Admin admin) {
        // Check if admin exists before updating
        adminRepository.getAdminById(admin.getId())
                .orElseThrow(() -> new NotFoundException(
                        localizationService.getMessage("admin.notFound", LocaleContextHolder.getLocale())));
        
        return adminRepository.updateAdmin(admin);
    }

    @Override
    public boolean deleteAdmin(int id) {
        // Check if admin exists before deleting
        adminRepository.getAdminById(id)
                .orElseThrow(() -> new NotFoundException(
                        localizationService.getMessage("admin.notFound", LocaleContextHolder.getLocale())));
        
        return adminRepository.deleteAdmin(id);
    }

    @Override
    public List<Admin> getAdmins(Map<String, String> params) {
        return adminRepository.getAdmins(params);
    }

    @Override
    public List<Admin> searchAdmins(Map<String, String> filters, Map<String, String> params) {
        return adminRepository.searchAdmins(filters, params);
    }

    @Override
    public List<Admin> getAdminsByCourse(Integer courseId, Map<String, String> params) {
        return adminRepository.getAdminsByCourse(courseId, params);
    }

    @Override
    public long countAdmins() {
        return adminRepository.countAdmins();
    }

    @Override
    public long countSearchResults(Map<String, String> filters) {
        return adminRepository.countSearchResults(filters);
    }

    @Override
    public long countAdminsByCourse(Integer courseId) {
        return adminRepository.countAdminsByCourse(courseId);
    }
}
