package com.example.Authentication.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.Authentication.dto.UserDetailsImpl;
import com.example.Authentication.model.User;
import com.example.Authentication.repo.UserRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService{
    private final UserRepo userRepo;
    @Override
    public UserDetails loadUserByUsername(String email) throws BadCredentialsException {
       User user =  userRepo.findByEmail(email).orElseThrow(()->new BadCredentialsException("Bad Credentials"));
        return new UserDetailsImpl(user);
    }
}
