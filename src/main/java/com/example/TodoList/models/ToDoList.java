package com.example.TodoList.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ToDoList")
public class ToDoList
{
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="listId")
    private Long listId;

    @Column(name="name")
    private String name;

    @Column(name="description")
    @Lob
    private String description;

    @OneToMany(fetch =LAZY)
    private List<ToDo> todos;

    @ManyToOne(fetch=LAZY)
    private Users users;

}
