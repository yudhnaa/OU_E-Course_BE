/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ou.controllers.webController;

import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String index(Model model, Locale locale) throws Exception {

        String helloWorld;

        helloWorld = userService.getUserById(99, locale).getFirstName() + " " + userService.getUserById(1, locale).getLastName();

        model.addAttribute("helloWorld", helloWorld);
        return "index";
    }

}
