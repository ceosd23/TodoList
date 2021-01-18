package com.example.TodoList.services;

import com.example.TodoList.exceptions.TodoListException;
import com.example.TodoList.models.ToDoList;
import com.example.TodoList.repositories.ToDoListRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor

public class ToDoListService
{
    private final ToDoListRepository toDoListRepository;
    private final UserAuth userAuth;

    @Transactional
    public ToDoList save(ToDoList toDoList)
    {
        toDoList.setUsers(userAuth.getCurrentUser());
        toDoListRepository.save(toDoList);
        return toDoList;
    }

    public ToDoList getByListId(Long id) {
        ToDoList toDoList = toDoListRepository.findByListIdAndUsers(id,userAuth.getCurrentUser())
                .orElseThrow(() -> new TodoListException("No List found with ID - " + id));
        return toDoList;
    }
    public ToDoList getAll()
    {
        ToDoList toDoList=toDoListRepository.findAllByUsers(userAuth.getCurrentUser())
                .orElseThrow(()->new TodoListException("Nothing found"));
        return toDoList;
    }
    @Transactional
    public boolean deletebyId(Long id)
    {
        ToDoList list=toDoListRepository.findByListIdAndUsers(id,userAuth.getCurrentUser()).orElseThrow(()->
                new TodoListException("No Such List"));
            toDoListRepository.delete(list);
            return true;
    }
}
