package com.seerlee.todoApi.test.controller;

import com.seerlee.todoApi.test.dto.TestReq;
import com.seerlee.todoApi.todo.dto.TodoRes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("test")
public class TestController {
    @GetMapping
    public String testController() {
        return "Hello World";
    }

    @GetMapping("/{id}")
    public String testControllerWithPathVariables(@PathVariable(required = false) int id) {
        return "Hello World! ID - " + id;
    }
    @GetMapping("/requestParam")
    public String testControllerWithRequestParam(@RequestParam(required = false) int id) {
        return "Hello World! ID - " + id;
    }

    @GetMapping("/requestBody")
    public String testControllerWithRequestBody(@RequestBody TestReq req) {
        return "Hello World! ID - "
                + req.getId()
                + " Message : "
                + req.getMessage();
    }
    @GetMapping("/responseBody")
    public ResponseEntity<?> testControllerWithResponseBody() {
        List<String> list = new ArrayList<>();
        list.add("Hello World! I'm TodoRes");
        TodoRes<String> response = TodoRes.<String> builder().data(list).build();
        return ResponseEntity.badRequest().body(response);
    }
}
