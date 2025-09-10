package com.dinh.todo.controller.admin;

import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DashboardController {

    @GetMapping("/admin")
    public String getDashBoard() {
        return "admin/dashboard/show";
    }


    @GetMapping("/session-info")
    @ResponseBody
    public String sessionInfo(HttpSession session) {
        return "Session ID: " + session.getId() +
                ", has SPRING_SECURITY_CONTEXT: " +
                (session.getAttribute("SPRING_SECURITY_CONTEXT") != null);
    }
}
