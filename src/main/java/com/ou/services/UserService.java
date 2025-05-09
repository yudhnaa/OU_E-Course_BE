/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ou.services;

import com.ou.pojo.User;

import java.util.Locale;

/**
 *
 * @author yudhna
 */

public interface UserService {

    User getUserById(int id, Locale locale) throws Exception;

}
