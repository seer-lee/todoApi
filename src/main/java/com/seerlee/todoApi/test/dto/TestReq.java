package com.seerlee.todoApi.test.dto;

import lombok.Data;
import lombok.Getter;

@Getter
public class TestReq {
    private int id;
    private String message;

    public void setId(int id) {
        this.id = id;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
