package com.example.TodoList.models;

import com.example.TodoList.exceptions.TodoListException;

import java.util.Arrays;

public enum PriorityType
{
    URGENT(1),NORMAL(0),INDEFINATE(-1),
    ;
    private int priority;
    PriorityType(int priority)
    {}
    public static PriorityType lookup(Integer priority)
    {
        return Arrays.stream(PriorityType.values())
                .filter(value->value.getPriority().equals(priority))
                .findAny()
                .orElseThrow(()->new TodoListException("Priority Not Found"));
    }
    public Integer getPriority()
    {
       return priority;
    }
}
