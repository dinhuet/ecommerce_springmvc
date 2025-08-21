package com.dinh.todo.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Size(min = 6, message = "username phai lon hon 6")
    @NotBlank
    private String username;

    @NotBlank
    @Size(min = 6, message = "password phai lon hon 6")
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Todos> todos = new ArrayList<>();

    public List<Todos> getTodos() {
        return todos;
    }

    public void setTodos(List<Todos> todos) {
        this.todos = todos;
    }

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
