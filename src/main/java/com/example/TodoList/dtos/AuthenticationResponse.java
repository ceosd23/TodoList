package com.example.TodoList.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {
    private String authenticateToken;
    private String username;
    private String refreshToken;
    private Instant expiresAt;
    private String message;


}

