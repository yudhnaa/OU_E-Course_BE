package com.ou.controllers.webController.adminController;

import com.ou.controllers.webController.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/admin")
public class StudentController {

    @Autowired
    private UserController userController;

    @GetMapping("/students")
    public String index() {
        return "dashboard/admin/student/student_list";
    }
}
