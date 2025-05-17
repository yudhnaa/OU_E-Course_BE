package com.ou.repositories.impl;
import com.ou.pojo.UserRole;
import com.ou.repositories.UserRoleRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRoleRepositoryImpl implements UserRoleRepository {

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
