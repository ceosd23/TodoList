package com.example.TodoList.controllers;

import com.example.TodoList.models.ToDoList;
import com.example.TodoList.services.ToDoListService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/todoList")
@AllArgsConstructor
public class ToDoListController
{
    private final ToDoListService toDoListService;
    @PostMapping("/")
    public ResponseEntity<ToDoList> createToDoList(@RequestBody ToDoList toDoList) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(toDoListService.save(toDoList));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ToDoList> getByListId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toDoListService.getByListId(id));
    }
    @GetMapping("/")
    public ResponseEntity<ToDoList> getAll()
    {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(toDoListService.getAll());
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteList(@PathVariable Long id)
    {
        return ResponseEntity.ok(toDoListService.deletebyId(id));
    }


}
