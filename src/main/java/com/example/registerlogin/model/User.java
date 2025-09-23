package com.example.registerlogin.model;

import jakarta.persistence.*;

// Entity = Table in the database
@Entity
@Table(name = "users")
public class User {

    // Primary key (id will auto-generate)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Column = database column
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password; // Hashed password

    // --- Encapsulation: private fields with getters/setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
