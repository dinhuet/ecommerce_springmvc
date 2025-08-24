package com.dinh.todo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OderController {

    @GetMapping("/admin/order")
    public String getDashBoard() {
        return "admin/order/show";
    }
}
