package com.ou.services.impl;


import com.ou.pojo.UserRole;
import com.ou.services.UserRoleSevice;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleSevice {
    @Override
    public List<UserRole> getAllUserRoles() {
        return List.of();
    }

    @Override
    public UserRole getUserRoleById(Integer id) {
        return null;
    }

    @Override
    public UserRole addUserRole(UserRole userRole) {
        return null;
    }

    @Override
    public UserRole updateUserRole(UserRole userRole) {
        return null;
    }

    @Override
    public boolean deleteUserRole(Integer id) {
        return false;
    }
}
