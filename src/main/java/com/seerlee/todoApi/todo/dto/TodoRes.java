package com.seerlee.todoApi.todo.dto;

import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TodoRes<T> {
    private String error;
    private List<T> data;

    public void setError(String error) {
        this.error = error;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
