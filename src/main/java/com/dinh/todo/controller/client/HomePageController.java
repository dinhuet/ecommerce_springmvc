package com.dinh.todo.controller.client;

import com.dinh.todo.models.Product;
import com.dinh.todo.models.User;
import com.dinh.todo.models.dto.RegisterDTO;
import com.dinh.todo.repository.RoleRepository;
import com.dinh.todo.service.ProductService;
import com.dinh.todo.service.RoleService;
import com.dinh.todo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class HomePageController {

    private final ProductService productService;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;


    public HomePageController(ProductService productService, UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.productService = productService;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String getHomePage(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegisterPage(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {

        return "client/auth/login";
    }

    @PostMapping("/register")
    public String handleRegister(@ModelAttribute("registerUser") RegisterDTO registerUser) {
        User user = userService.registerDTOtoUser(registerUser);

        String hashPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(hashPassword);
        user.setRole(roleService.findRoleByName("USER"));
        userService.save(user);

        return "redirect:/login";
    }
}
