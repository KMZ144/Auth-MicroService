package com.example.Authentication.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.Authentication.model.Role;
import com.example.Authentication.model.User;
import com.example.Authentication.repo.UserRepo;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class StartUpConfig implements CommandLineRunner{

    
    private final UserRepo userRepo;


    
   // private final PasswordEncoder passwordEncoder;
    // @Autowired
//   public StartUpConfig(final UserRepo repo,PasswordEncoder passwordEncoder) {
//     this.userRepo = repo;
//     this.passwordEncoder=passwordEncoder;
//   }

    @Override
    public void run(String... args) throws Exception {
        User user = new User();
        user.setEmail("eman@gmail.com");
     //   user.setPassword(passwordEncoder.encode("123"));
     user.setRole(Role.CHECKER);
     user.setLastLogin(LocalDateTime.now());
        userRepo.save(user);
    }
}
