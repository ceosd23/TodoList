package com.example.TodoList.services;

import com.example.TodoList.exceptions.TodoListException;
import com.example.TodoList.models.RefreshToken;
import com.example.TodoList.repositories.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@Service
@Transactional
public class RefreshTokenService
{
    private final RefreshTokenRepository refreshTokenRepository;
    public RefreshToken generateRefreshToken()
    {
        RefreshToken refreshToken=new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());
        refreshTokenRepository.save(refreshToken);
        return refreshToken;

    }
    void validateRefreshToken(String token)
    {
        refreshTokenRepository.findByToken(token).orElseThrow(() -> new TodoListException("Invalid"));
    }
    public void deleteRefreshToken(String token)
    {

        refreshTokenRepository.deleteByToken(token);
    }

}