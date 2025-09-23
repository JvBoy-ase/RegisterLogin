package com.example.registerlogin.model;

import jakarta.persistence.*;
import java.time.LocalDate; // ✅ for birthdate

// Entity = Table in the database
@Entity
@Table(name = "users")
public class User {

    // Primary key (id will auto-generate)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Column = database column
    @Column(nullable = false, unique = true) // ✅ username must be unique
    private String username;

    @Column(nullable = false)
    private String password; // ✅ hashed password

    @Column(nullable = false, unique = true) 
    private String email; // ✅ new field, also unique

    @Column(nullable = false)
    private String number; // ✅ new field for phone number

    private String address; // ✅ new field for address

    private LocalDate birthdate; // ✅ new field for birthdate

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

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }
}
