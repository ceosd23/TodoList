package com.example.TodoList.repositories;

import com.example.TodoList.models.ToDo;
import com.example.TodoList.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ToDoRepository extends JpaRepository<ToDo, Long> {

   ToDo findByTodoIdAndUsers(Long toDoId,Users users);
   ToDo findByTaskNameAndUsers(String taskName, Users currentUser);
   Optional<ToDo> findByTargetDateAndUsers(Date date, Users users);
   Optional<ToDo> findByIsDoneAndUsers(Boolean isDone,Users users);
}
