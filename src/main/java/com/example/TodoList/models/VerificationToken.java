package com.example.TodoList.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Token")
public class VerificationToken
{
    @Id
    @GeneratedValue
    private Long id;
    private String token;

    @OneToOne(fetch = LAZY)
    private User user;
    private Instant expiryDate;

}
