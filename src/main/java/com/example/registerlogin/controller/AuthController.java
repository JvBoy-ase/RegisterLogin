package com.example.registerlogin.controller;

import com.example.registerlogin.model.User;
import com.example.registerlogin.repository.UserRepository;
import com.example.registerlogin.security.JwtUtil;
import com.example.registerlogin.model.RegisterRequest; // ✅ new DTO for registration
import com.example.registerlogin.model.AuthResponse;
import com.example.registerlogin.model.LoginRequest;   // still use for login
import com.example.registerlogin.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, UserRepository userRepository, JwtUtil jwtUtil) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }
    
    @GetMapping("/profile")
    public User getProfile(@RequestHeader("Authorization") String authHeader) {
        // 1. Remove "Bearer " prefix
        String token = authHeader.replace("Bearer ", "");

        // 2. Extract username from token
        String username = jwtUtil.extractUsername(token);

        // 3. Fetch user from DB
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


    // ✅ Register endpoint using RegisterRequest (extra fields supported)
    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {
        return userService.registerUser(request);
    }

    // ✅ Login endpoint (still only needs username + password)
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        boolean success = userService.loginUser(request.getUsername(), request.getPassword());
        if (!success) {
            throw new RuntimeException("Invalid username or password");
        }

        // 1. Generate JWT
        String token = jwtUtil.generateToken(request.getUsername());

        // 2. Fetch user details from DB
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3. Return both token + user profile
        return new AuthResponse(token, user);
    }
}
