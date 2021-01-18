package com.example.TodoList.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="User")
public class User
{
    @Id
    @GeneratedValue
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
