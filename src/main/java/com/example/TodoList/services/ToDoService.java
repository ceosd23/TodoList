package com.example.TodoList.services;

import com.example.TodoList.models.ToDo;
import com.example.TodoList.repositories.ToDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToDoService
{
    @Autowired
    private ToDoRepository toDoRepository;
    public ToDo saveItem(ToDo todo)
    {
        return toDoRepository.save(todo);
    }

}
