package com.example.TodoList.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Users
{
    @Id
    @GeneratedValue(strategy =GenerationType.SEQUENCE)
    @Column(name="userId")
    private Long userId;
    @NotBlank(message="UserName is Required")
    @Column(name="username")
    private String username;
    @NotBlank(message="Password is Required")
    @Column(name="password")
    private String password;

    @Email
    @NotEmpty(message="Email is Required")
    @Column(name="email")
    private String email;
    @Column(name="enabled")
    private boolean enabled;

}
