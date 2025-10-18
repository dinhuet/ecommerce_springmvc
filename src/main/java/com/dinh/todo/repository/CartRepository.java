package com.dinh.todo.repository;

import com.dinh.todo.models.Cart;
import com.dinh.todo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findByUser(User user);
    boolean existsByUser(User user);
}