package com.example.TodoList.exceptions;

public class TodoListException extends RuntimeException {
    public TodoListException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public TodoListException(String exMessage) {
        super(exMessage);
    }
}
