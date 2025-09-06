package com.dinh.todo.service;

import com.dinh.todo.models.Role;
import com.dinh.todo.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    public Role findRoleByName(String name) {
            return roleRepository.findByName(name).orElseThrow(() -> new EntityNotFoundException("Role not found"));
    }
}
