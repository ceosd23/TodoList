package com.example.TodoList.exceptions;

public class ToDoListNotFoundException extends RuntimeException {
    public ToDoListNotFoundException(String message) {
        super(message);
    }
}
