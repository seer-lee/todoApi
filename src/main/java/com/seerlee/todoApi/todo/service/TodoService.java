package com.seerlee.todoApi.todo.service;

import com.seerlee.todoApi.todo.entity.TodoEntity;
import com.seerlee.todoApi.todo.repo.TodoRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<TodoEntity> update(final TodoEntity entity) {
        validate(entity);
        final Optional<TodoEntity> original = todoRepo.findById(entity.getId());

        original.ifPresent(todo -> {
            todo.setTitle(entity.getTitle());
            todo.setDone(entity.isDone());
            todoRepo.save(todo);
        });
        return retrieve(entity.getUserId());
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

    public List<TodoEntity> delete(final TodoEntity entity) {
        validate(entity);
        try {
            todoRepo.delete(entity);
        } catch (Exception e) {
            log.error("error deleting entity ", entity.getId(), e);
            throw new RuntimeException("error deleting entity "+entity.getId());
        }
        return retrieve(entity.getUserId());
    }
}
