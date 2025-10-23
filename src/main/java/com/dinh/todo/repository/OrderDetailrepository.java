package com.dinh.todo.repository;

import com.dinh.todo.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailrepository extends JpaRepository<OrderDetail, Long> {
}
