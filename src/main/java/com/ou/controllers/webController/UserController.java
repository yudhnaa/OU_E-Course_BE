package com.ou.controllers.webController;

import com.ou.pojo.User;
import com.ou.pojo.UserRole;
import com.ou.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = "/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);

        return "register";
    }

    @PostMapping(value = "/register")
    public String register(@ModelAttribute ("user") User user) {

        User u = new User(0, "Smith", "John", "1985-05-15", "admin_test", "1", "/avatars/admin1.jpg", "admin1@ecourse.com", true);
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setUserRoleId(new UserRole(1, "admin"));

        userService.addUser(u);

        return "register";
    }
}
