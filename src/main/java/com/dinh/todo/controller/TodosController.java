package com.dinh.todo.controller;

import com.dinh.todo.models.Todos;
import com.dinh.todo.models.User;
import com.dinh.todo.service.TodosService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@Controller
public class TodosController {

    private final TodosService todosService;
    public TodosController(TodosService todosService) {
        this.todosService = todosService;
    }

    @GetMapping("/")
    public String listTodos(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        System.out.println(user);
        List<Todos> todos = todosService.getTodosByUser(user);
        model.addAttribute("todos", todos);
        return "list";
    }
}
