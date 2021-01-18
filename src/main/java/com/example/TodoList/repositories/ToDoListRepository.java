package com.example.TodoList.repositories;

import com.example.TodoList.models.ToDoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long>
{
    Optional<ToDoList> findByListId(Long listId);
}
