package com.example.registerlogin.repository;

import com.example.registerlogin.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// Repository = handles database queries (Abstraction)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
