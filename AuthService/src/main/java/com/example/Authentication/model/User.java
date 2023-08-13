package com.example.Authentication.model;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime lastLogin;

   // private boolean isActive;

    // private String password;

}
