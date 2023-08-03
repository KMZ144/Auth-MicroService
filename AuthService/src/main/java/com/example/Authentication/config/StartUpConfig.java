package com.example.Authentication.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Authentication.model.User;
import com.example.Authentication.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class StartUpConfig implements CommandLineRunner{

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("eman@gmail.com");
        user.setPassword(passwordEncoder.encode("123"));
        userRepo.save(user);
    }
}
