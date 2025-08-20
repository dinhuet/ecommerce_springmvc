package com.dinh.todo.controller;

import com.dinh.todo.models.User;
import com.dinh.todo.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

//    @GetMapping("/user")
//    @ResponseBody
//    public ResponseEntity<String> user() {
//
//            User user = userService.findByUsername("1234");
//            if (user == null) {
//                throw new RuntimeException("User not found");
//            }
//            return ResponseEntity.ok(user.getUsername());
//    }

    @PostMapping("/register")
    public ModelAndView newUserRegister(@ModelAttribute User user, RedirectAttributes redirectAttributes) {
        User exist =  userService.findByUsername(user.getUsername());
        ModelAndView mv = new ModelAndView();
        if (exist != null) {
            redirectAttributes.addFlashAttribute("message", "Username is already exist");
        } else {
            userService.saveUser(user);
            redirectAttributes.addFlashAttribute("message", "User has been registered successfully");
        }
        mv.setViewName("redirect:/login");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView loginUser(@ModelAttribute User user, Model model ,HttpSession session) {
        ModelAndView mv = new ModelAndView();
        User user1 = userService.existsUser(user);
        if (user1 != null) {
            session.setAttribute("user", user1);
            mv.setViewName("redirect:/");
        } else {
            model.addAttribute("message", "Username is not already exist");
            mv.setViewName("login");
        }
        return mv;
    }
}
