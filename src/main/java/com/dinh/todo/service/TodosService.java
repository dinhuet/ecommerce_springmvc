package com.dinh.todo.service;

import com.dinh.todo.models.Todos;
import com.dinh.todo.models.User;
import com.dinh.todo.repository.TodosRepository;
import com.dinh.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodosService {
    private final TodosRepository todosRepository;
    private final UserRepository userRepository;
    @Autowired
    public TodosService(TodosRepository todosRepository, UserRepository userRepository) {
        this.todosRepository = todosRepository;
        this.userRepository = userRepository;
    }

    public void addTodos(String title, String description, String username) {
        User user = userRepository.findByUsername(username);
        Todos todos = new Todos();
        todos.setTitle(title);
        todos.setDescription(description);
        todos.setUser(user);
        todosRepository.save(todos);
    }

    public List<Todos> getTodos(String username) {
        return todosRepository.findAll();
    }

    public List<Todos> getTodosByUser(User user) {
        return todosRepository.findByUser(user);
    }
}
