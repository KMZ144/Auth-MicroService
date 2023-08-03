package com.example.Authentication.repo;

import java.util.Optional;

import com.example.Authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    
}
