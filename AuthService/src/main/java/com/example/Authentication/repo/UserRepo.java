package com.example.Authentication.repo;

import java.util.Optional;

import com.example.Authentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    
}
