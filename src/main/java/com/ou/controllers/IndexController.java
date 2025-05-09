/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.controllers;

import com.ou.services.UserService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Locale;

/**
 *
 * @author yudhna
 */

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String index(Model model, Locale locale) {

        String helloWorld;
        try {
             helloWorld = userService.getUserById(99, locale).getFirstName() + " " + userService.getUserById(1, locale).getLastName();
        } catch (Exception ex){
            helloWorld = ex.getMessage();
        }

        model.addAttribute("helloWorld", helloWorld);
        return "index";
    }

}
