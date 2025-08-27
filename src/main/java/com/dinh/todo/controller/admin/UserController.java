package com.dinh.todo.controller.admin;

import com.dinh.todo.models.Role;
import com.dinh.todo.models.User;
import com.dinh.todo.service.RoleService;
import com.dinh.todo.service.UploadService;
import com.dinh.todo.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Controller
public class UserController {
    private final UserService userService;
    private final RoleService roleService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, RoleService roleService, UploadService uploadService
    , PasswordEncoder  passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.uploadService = uploadService;
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

        String avatar = uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = passwordEncoder.encode(newUser.getPassword());

        newUser.setAvatar(avatar);
        newUser.setPassword(hashPassword);
        userService.save(newUser);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/createUser")
    public String createUser(Model moldel) {
        User user = new User();
        moldel.addAttribute("newUser", user);

        List<Role> roles = roleService.findAll();
        moldel.addAttribute("roles", roles);

        return "/admin/user/createUser";
    }
}
