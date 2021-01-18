package com.example.TodoList.repositories;

import com.example.TodoList.models.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository
{
    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
