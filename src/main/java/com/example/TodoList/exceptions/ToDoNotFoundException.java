package com.example.TodoList.exceptions;

public class ToDoNotFoundException extends RuntimeException
{
    public ToDoNotFoundException(String message)
    {
        super(message);
    }
}
