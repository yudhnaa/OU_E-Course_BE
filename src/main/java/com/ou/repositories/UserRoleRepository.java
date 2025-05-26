package com.ou.repositories;

import com.ou.pojo.UserRole;

import java.util.List;

public interface UserRoleRepository {
    List<UserRole> getAllUserRoles();
    UserRole getUserRoleById(Integer id);
    UserRole getUserRoleByName(String name);

    UserRole addUserRole(UserRole userRole);
    UserRole updateUserRole(UserRole userRole);
    boolean deleteUserRole(Integer id);
}
