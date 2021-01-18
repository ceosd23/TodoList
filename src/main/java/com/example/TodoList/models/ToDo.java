package com.example.TodoList.models;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import static javax.persistence.FetchType.LAZY;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ToDo")
public class ToDo
{
    @Id
    @GeneratedValue
    @Column(name="todoId")
    private Long todoId;
    @NotEmpty
    @Column(name="taskName")
    private String taskName;
    @Column(name="isDone")
    private Boolean isDone=false;
    @Column(name="createdAt")
    private Date createdAt=new Date();
    @Column(name="targetDate")
    private Date targetDate;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "listId", referencedColumnName = "listId")
    private ToDoList toDoList;


}
