package com.example.registerlogin.service;

import com.example.registerlogin.model.RegisterRequest;
import com.example.registerlogin.model.User;
import com.example.registerlogin.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.Optional;

// Service = business logic (Encapsulation + Polymorphism can be applied here)
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection (Dependency Injection)
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Business logic for registering a new user
    public User registerUser(RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword())); // secure password
        user.setEmail(request.getEmail());
        user.setNumber(request.getNumber());
        user.setAddress(request.getAddress());
        user.setBirthdate(request.getBirthdate());
        return userRepository.save(user); // saves into database
    }

    // Login user
    public boolean loginUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            // Check raw password with hashed password
            return passwordEncoder.matches(password, userOpt.get().getPassword());
        }
        return false;
    }
}
