package com.ou.services;

import com.ou.pojo.UserRole;
import java.util.List;

public interface UserRoleService {
    List<UserRole> getAllUserRoles();
    UserRole getUserRoleById(Integer id);
    UserRole addUserRole(UserRole userRole);
    UserRole updateUserRole(UserRole userRole);
    boolean deleteUserRole(Integer id);
    UserRole getUserRoleByName(String name);
}
