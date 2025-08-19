package com.dinh.todo;

import com.dinh.todo.models.Todos;
import com.dinh.todo.models.User;
import com.dinh.todo.service.TodosService;
import com.dinh.todo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestHibernate implements CommandLineRunner {
    private final UserService userService;
    private final TodosService todosService;

    public TestHibernate(UserService userService, TodosService todosService) {
        this.userService = userService;
        this.todosService = todosService;
    }


    @Override
    public void run(String... args) throws Exception {
        User user = userService.findByUsername("23020043");
        System.out.println(user.getUsername());
    }
}
