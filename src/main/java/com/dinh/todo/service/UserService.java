package com.dinh.todo.service;

import com.dinh.todo.models.User;
import com.dinh.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        if (findByUsername(user.getUsername()) == null) {
            userRepository.save(user);
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User existsUser(User user) {
        return findByUsername(user.getUsername());
    }
}
