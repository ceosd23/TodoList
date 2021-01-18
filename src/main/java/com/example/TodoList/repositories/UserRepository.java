package com.example.TodoList.repositories;

import com.example.TodoList.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUserId(Long userId);
    Optional<Users> findByUsername(String userName);
    Optional<Users> findByEmail(String email);
}

