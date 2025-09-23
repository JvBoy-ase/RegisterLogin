package com.example.registerlogin.controller;

import com.example.registerlogin.security.JwtUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfileController {

    private final JwtUtil jwtUtil;

    public ProfileController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // ✅ Protected endpoint using JWT
    @GetMapping("/profile")
    public String getProfile(@RequestHeader("Authorization") String authHeader) {
        // Remove "Bearer " prefix
        String token = authHeader.substring(7);

        // Validate token before extracting username
        if (!jwtUtil.validateToken(token)) {
            return "Invalid or expired token!";
        }

        String username = jwtUtil.extractUsername(token);
        return "Profile info: Logged in as " + username;
    }
}
