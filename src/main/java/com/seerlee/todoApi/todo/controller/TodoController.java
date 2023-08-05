package com.seerlee.todoApi.todo.controller;

import com.seerlee.todoApi.todo.dto.TodoDto;
import com.seerlee.todoApi.todo.dto.TodoRes;
import com.seerlee.todoApi.todo.entity.TodoEntity;
import com.seerlee.todoApi.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/test")
    public ResponseEntity<?> testTodo() {
        String str = todoService.testService();
        List<String> list = new ArrayList<>();
        list.add(str);
        TodoRes<String> result = TodoRes.<String>builder()
                .data(list)
                .build();
        return ResponseEntity.ok()
                .body(result);
    }

    @PostMapping
    public ResponseEntity<?> createTodo(@RequestBody TodoDto dto) {
        try {
            String temporaryUserId = "temporary-user";
            TodoEntity entity = TodoDto.toEntity(dto);
            entity.setId(null);
            entity.setUserId(temporaryUserId);

            List<TodoEntity> entities = todoService.create(entity);
            List<TodoDto> dtos = entities.stream()
                    .map(TodoDto::new)
                    .collect(Collectors.toList());;

            TodoRes<TodoDto> res = TodoRes.<TodoDto>builder()
                    .data(dtos)
                    .build();
            return ResponseEntity.ok().body(res);
        } catch (Exception e) {
            String error = e.getMessage();
            TodoRes<TodoDto> res = TodoRes.<TodoDto>builder()
                    .error(error)
                    .build();
            return ResponseEntity.badRequest().body(res);
        }
    }
}
