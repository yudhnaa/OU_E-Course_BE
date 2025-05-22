package com.ou.controllers.webController.adminController;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class LecturerController {

    @GetMapping("/lecturers")
    public String lecturers(
            Model model
    ) {

        return "/dashboard/admin/lecturer/lecturer";
    }
}
