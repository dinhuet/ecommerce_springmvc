package com.dinh.todo.controller.admin;

import jakarta.persistence.Entity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    @GetMapping("/admin")
    public String getDashBoard() {
        return "admin/dashboard/show";
    }

//    @GetMapping("/admin/user")
//    public String getDashBoardUser() {
//        return "admin/user/show";
//    }
}
