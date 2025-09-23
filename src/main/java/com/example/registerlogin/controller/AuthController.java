package com.example.registerlogin.controller;

import com.example.registerlogin.model.User;
import com.example.registerlogin.model.RegisterRequest; // ✅ new DTO for registration
import com.example.registerlogin.model.LoginRequest;   // still use for login
import com.example.registerlogin.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Register endpoint using RegisterRequest (extra fields supported)
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    // ✅ Login endpoint (still only needs username + password)
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {
        boolean success = userService.loginUser(request.getUsername(), request.getPassword());
        return success ? "Login successful!" : "Invalid username or password";
    }
}
