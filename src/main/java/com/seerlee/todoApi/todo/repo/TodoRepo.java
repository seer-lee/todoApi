package com.seerlee.todoApi.todo.repo;

import com.seerlee.todoApi.todo.entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepo extends JpaRepository<TodoEntity, String> {
    List<TodoEntity> findByUserId(String userId);
}
