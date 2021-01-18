package com.example.TodoList.controllers;
import com.example.TodoList.models.ToDo;
import com.example.TodoList.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @PostMapping(value = "/new")
    public ResponseEntity<ToDo> add(@RequestBody ToDo todo) {
        return ResponseEntity.ok(toDoService.saveItem(todo));
    }

}
