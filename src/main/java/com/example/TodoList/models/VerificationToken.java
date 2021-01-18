package com.example.TodoList.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.Instant;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.AUTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Token")
public class VerificationToken
{
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String token;
    private Instant expiryDate;
    @OneToOne(fetch = LAZY)
    private Users user;
}
