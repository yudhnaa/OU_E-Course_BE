/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.pojo.User;

import java.util.List;

/**
 *
 * @author yudhna
 */
public interface UserService {
    public User getUserById(int id);
    List<User> getAllUsers();
    User updateUser(User user);
    boolean deleteUser(int id);
    User addUser(User user);
    User getUserByUsername(String username);
    List<User> getActiveUsers(boolean isActive);
}
