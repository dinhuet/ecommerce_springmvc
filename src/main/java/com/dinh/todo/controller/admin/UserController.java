package com.dinh.todo.controller.admin;

import com.dinh.todo.models.Role;
import com.dinh.todo.models.User;
import com.dinh.todo.service.RoleService;
import com.dinh.todo.service.UserService;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final ServletContext servletContext;
    @Autowired
    public UserController(UserService userService, RoleService roleService, ServletContext servletContext) {
        this.userService = userService;
        this.roleService = roleService;
        this.servletContext = servletContext;
    }

    @GetMapping("/admin/user")
    public String getUserPage(Model model) {
        List<User> users = userService.getUsers();
        model.addAttribute("users", users);
        return "admin/user/show";
    }

    @PostMapping("/admin/user/createUser")
    public String createUser1(@ModelAttribute User newUser
    , @RequestParam("hoidanItFile") MultipartFile file) {
        try {
            byte[] bytes = file.getBytes();
            String uploadDir = "uploads/avatar/";

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

// Create the file on server
            File serverFile = new File(dir.getAbsolutePath() + File.separator +
                    System.currentTimeMillis() + "-" + file.getOriginalFilename());
            BufferedOutputStream stream = new BufferedOutputStream(
                    new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

       // userService.save(newUser);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/createUser")
    public String createUser(Model moldel) {
        User user = new User();
        moldel.addAttribute("newUser", user);

        List<Role> roles = roleService.findAll();
        moldel.addAttribute("roles", roles);

        return "createUser";
    }
}
