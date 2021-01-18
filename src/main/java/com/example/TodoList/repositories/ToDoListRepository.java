package com.example.TodoList.repositories;

import com.example.TodoList.models.ToDoList;
import com.example.TodoList.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ToDoListRepository extends JpaRepository<ToDoList, Long>
{
    Optional<ToDoList> findByListIdAndUsers(Long listId,Users users);
    Optional<ToDoList> findAllByUsers(Users user);
}
