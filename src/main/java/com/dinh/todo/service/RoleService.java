package com.dinh.todo.service;

import com.dinh.todo.models.Role;
import com.dinh.todo.repository.RoleRepository;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
