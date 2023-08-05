package com.seerlee.todoApi.todo.service;

import com.seerlee.todoApi.todo.entity.TodoEntity;
import com.seerlee.todoApi.todo.repo.TodoRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TodoService {
    private final TodoRepo todoRepo;
    public String testService() {
        TodoEntity entity = TodoEntity.builder().title("My first todo item").build();
        todoRepo.save(entity);

        TodoEntity savedEntity = todoRepo.findById(entity.getId()).get();
        return savedEntity.getTitle();
    }

    public List<TodoEntity> create(final TodoEntity entity) {
        validate(entity);
        todoRepo.save(entity);
        log.info("Entity Id : {} is saved.", entity.getId());
        return retrieve(entity.getUserId());
    }

    public List<TodoEntity> retrieve(final String userId) {
        return todoRepo.findByUserId(userId);
    }

    private static void validate(TodoEntity entity) {
        if (entity == null) {
            log.warn("Entity cannot be null.");
            throw new RuntimeException("Entity cannot be null.");
        }

        if (entity.getUserId() == null) {
            log.warn("unknown user.");
            throw new RuntimeException("Unknown user.");
        }
    }
}
