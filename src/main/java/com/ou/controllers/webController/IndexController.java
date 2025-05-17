/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.controllers.webController;

import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Locale;

/**
 *
 * @author yudhna
 */

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

//    @RequestMapping("/")
//    public String index(Model model, Principal principal) throws Exception {
//
//        if (principal != null) {
//            String username = principal.getName();
//            model.addAttribute("loged_username", username);
//        }
//
//        return "index";
//    }

    @GetMapping("/")
    public String index() {
        return "dashboard/index";
    }

    @RequestMapping("/user/{userId}")
    public String userDetail(Model model, @PathVariable("userId") int userId) throws Exception {
        String helloWorld;

        helloWorld = userService.getUserById(userId).getFirstName() + " " + userService.getUserById(userId).getLastName();

        model.addAttribute("helloWorld", helloWorld);
        return "index";
    }

}
