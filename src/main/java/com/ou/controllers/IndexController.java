/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.controllers;

import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author yudhna
 */

@Controller
public class IndexController {

//    @Autowired
//    private UserService userService;
//
//    @RequestMapping("/")
//    public String index(Model model) {
//        model.addAttribute("helloWorld", userService.getUserById(1).getFirstName());
//        return "layout";
//    }

    @GetMapping("/")
    public String index() {
        return "dashboard/index";
    }

}
