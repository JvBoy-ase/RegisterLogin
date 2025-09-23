package com.example.registerlogin.controller;

import com.example.registerlogin.model.User;
import com.example.registerlogin.model.LoginRequest;
import com.example.registerlogin.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Register endpoint
    @PostMapping("/register")
    public User register(@RequestBody LoginRequest request) {
        return userService.registerUser(request.getUsername(), request.getPassword());
    }

    // Login endpoint
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        boolean success = userService.loginUser(request.getUsername(), request.getPassword());
        return success ? "Login successful!" : "Invalid username or password";
    }
}
