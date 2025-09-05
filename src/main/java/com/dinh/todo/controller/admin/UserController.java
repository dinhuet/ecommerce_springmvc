package com.dinh.todo.controller.admin;

import com.dinh.todo.models.Role;
import com.dinh.todo.models.User;
import com.dinh.todo.service.RoleService;
import com.dinh.todo.service.UploadService;
import com.dinh.todo.service.UserService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
    public String createUser1(@Valid @ModelAttribute("newUser") User newUser, BindingResult newUserBindingResult
    , @RequestParam("hoidanItFile") MultipartFile file, Model model) {

        // validate
        List<FieldError> fieldErrors = newUserBindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            System.out.println(">>>>>>" + fieldError.getField() + " " + fieldError.getDefaultMessage());
        }

        if (newUserBindingResult.hasErrors()) {
            // nếu modelAttribute ko có tên thì nó lưu trong model là user chứ ko phải newUser
            //model.addAttribute("newUser", newUser);

            List<Role> roles = roleService.findAll();
            model.addAttribute("roles", roles);

            return "admin/user/createUser";
        }

        String avatar = uploadService.handleSaveUploadFile(file, "avatar");
        String hashPassword = passwordEncoder.encode(newUser.getPassword());

        newUser.setAvatar(avatar);
        newUser.setPassword(hashPassword);
        userService.save(newUser);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/createUser")
    public String createUser(Model model) {
        User user = new User();
        model.addAttribute("newUser", user);

        List<Role> roles = roleService.findAll();
        model.addAttribute("roles", roles);

        return "admin/user/createUser";
    }
}
