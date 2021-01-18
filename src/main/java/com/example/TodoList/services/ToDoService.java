package com.example.TodoList.services;

import com.example.TodoList.models.PriorityType;
import com.example.TodoList.models.ToDo;
import com.example.TodoList.repositories.ToDoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ToDoService
{

    private final ToDoRepository toDoRepository;
    private final UserAuth userAuth;
    @Transactional
    public ToDo saveToDo(ToDo todo)
    {
        todo.setUsers(userAuth.getCurrentUser());
        return toDoRepository.save(todo);
    }
    @Transactional
    public ToDo editToDo(ToDo toDo)
    {
        ToDo existingToDo=toDoRepository.findByTodoIdAndUsers(toDo.getTodoId(),userAuth.getCurrentUser());
        if(existingToDo!=null)
        {
            existingToDo.setPriorityType(toDo.getPriorityType());
            existingToDo.setCreatedAt(new Date());
            existingToDo.setTargetDate(toDo.getTargetDate());
            existingToDo.setIsDone(toDo.getIsDone());
            toDoRepository.save(existingToDo);
            return existingToDo;
        }
        return null;
    }
    @Transactional
    public Boolean deleteTodo(Long id)
    {
        ToDo todo=toDoRepository.findByTodoIdAndUsers(id,userAuth.getCurrentUser());
        if(todo!=null)
        {
            toDoRepository.delete(todo);
            return true;
        }
        return false;
    }

    public ToDo findByToDoId(Long id)
    {
        ToDo todo=toDoRepository.findByTodoIdAndUsers(id,userAuth.getCurrentUser());
        if(todo!=null)
        {
            return todo;
        }
        return new ToDo();
    }
    public ToDo setPriority(Long id, PriorityType priorityType)
    {
        ToDo todo=toDoRepository.findByTodoIdAndUsers(id,userAuth.getCurrentUser());
        if(todo!=null)
        {
            todo.setPriorityType(priorityType);
            return todo;
        }
        return todo;
    }
    public ToDo findByTaskName(String taskName)
    {
        ToDo todo=toDoRepository.findByTaskNameAndUsers(taskName,userAuth.getCurrentUser());
        if(todo!=null)
        {
            return todo;
        }
        return todo;
    }
    public List<ToDo> findBytargetDate(Date date)
    {
        Optional<ToDo> list=toDoRepository.findByTargetDateAndUsers(date,userAuth.getCurrentUser());
        List<ToDo> result=new ArrayList<>();
        list.filter(a->a.getIsDone()==false).ifPresent(result::add);
        return result;
    }
    public List<ToDo> findByState(Boolean isDone)
    {
        Optional<ToDo> list=toDoRepository.findByIsDoneAndUsers(isDone,userAuth.getCurrentUser());
        List<ToDo> result=new ArrayList<>();
        list.ifPresent(result::add);
        return result;
    }
}
