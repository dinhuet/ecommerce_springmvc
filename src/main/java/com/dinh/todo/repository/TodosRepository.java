package com.dinh.todo.repository;

import com.dinh.todo.models.Todos;
import com.dinh.todo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodosRepository extends JpaRepository<Todos, Integer> {

    List<Todos> findByUser(User user);
}
