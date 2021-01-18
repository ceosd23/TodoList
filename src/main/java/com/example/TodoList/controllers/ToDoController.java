package com.example.TodoList.controllers;
import com.example.TodoList.models.PriorityType;
import com.example.TodoList.models.ToDo;
import com.example.TodoList.services.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/todos")
public class ToDoController {
    @Autowired
    private ToDoService toDoService;

    @PostMapping(value = "/")
    public ResponseEntity<ToDo> add(@RequestBody ToDo todo) {

        return ResponseEntity.ok(toDoService.saveToDo(todo));
    }

    @PutMapping("/edit")
    public ResponseEntity<ToDo> edit(@RequestBody ToDo todo)
    {
        return ResponseEntity.ok(toDoService.editToDo(todo));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteTodoItem(@PathVariable Long id) {
        return ResponseEntity.ok(toDoService.deleteTodo(id));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ToDo> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(toDoService.findByToDoId(id));
    }
    @PutMapping("/{id}/priority")
    public ResponseEntity<ToDo> setPriority(@PathVariable Long id, @RequestBody PriorityType priorityType)
    {
        return ResponseEntity.ok(toDoService.setPriority(id,priorityType));
    }
    @GetMapping("/{taskName}")
    public ResponseEntity<ToDo> findByTaskName(@PathVariable String taskName)
    {
        return ResponseEntity.ok(toDoService.findByTaskName(taskName));
    }
    @GetMapping("/targetDate/{targetDate}")
    public List<ToDo> findByTargetDate(@PathVariable Date targetDate)
    {
        return toDoService.findBytargetDate(targetDate);

    }
    @GetMapping("/{isDone}")
    public List<ToDo> findByState(@PathVariable Boolean isDone)
    {
        return toDoService.findByState(isDone);
    }



}
